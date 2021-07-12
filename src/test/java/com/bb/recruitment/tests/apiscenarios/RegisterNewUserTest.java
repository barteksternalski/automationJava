package com.bb.recruitment.tests.apiscenarios;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import com.bb.recruitment.datamodel.Errors;
import com.bb.recruitment.datamodel.User;
import com.bb.recruitment.tags.SuiteNames;


import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Feature("Verification of endpoint for new user registration")
@Execution(ExecutionMode.CONCURRENT)
public class RegisterNewUserTest extends BaseApiTest {

    @Test
    @Tag(SuiteNames.API_TESTS)
    @DisplayName("Verify new user can be registered successfully")
    void verifyNewUserCanBeCreatedSuccessfully() throws IOException {

        //create new user
        Response response = registerNewUser(generateDataForRegisteringNewUser());

        //verify user was created successfully
        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);

        //parse response data to user model
        User createdUser = parseResponseUserData(response.asString());

        //send request to get current user data
        String endpoint = propertyLoader.loadProperty("currentUser");
        response = given()
                .filter(new AllureRestAssured())
                .auth().basic(basicAuthUsername, basicAuthPassword)
                .header("jwtauthorization", "Token ".concat(createdUser.getUser().getToken()))
                .when()
                .get(endpoint);

        //verify request was handled successfully
        assertThat(response.getStatusCode())
                .as("There was something wrong with retrieving created user data")
                .isEqualTo(200);

        //verify correct data was sent in the response
        assertThat(parseResponseUserData(response.asString()).getUser().getEmail())
                .as("Retrieved email is different that created one")
                .isEqualTo(createdUser.getUser().getEmail());
        assertThat(parseResponseUserData(response.asString()).getUser().getPassword())
                .as("Retrieved password is different that created one")
                .isEqualTo("Test123!");
    }

    @ParameterizedTest
    @MethodSource("unsuccessfulUserCreationData")
    @Tag(SuiteNames.API_TESTS)
    @DisplayName("Verify new user can be registered successfully")
    void verifiedUnsuccessfulUserCreation(String username, String email, String errorMessage) throws IOException {

        //create new user
        User existingUser = parseResponseUserData(registerNewUser(generateDataForRegisteringNewUser()).asString());

        //get data model for additional user
        User anotherUser = generateDataForRegisteringNewUser();

        //override anotherUser data to generate errors
        if (username.equals("blank"))
            anotherUser.getUser().setUsername("");
        else if (username.equals("taken"))
            anotherUser.getUser().setUsername(existingUser.getUser().getUsername());
        if (email.equals("blank"))
            anotherUser.getUser().setEmail("");
        else if (email.equals("taken"))
            anotherUser.getUser().setEmail(existingUser.getUser().getEmail());

        //send request to create another user
        Response response = registerNewUser(anotherUser);

        //parse response
        Errors errors = parseResponseErrorsData(response.asString());

        //verify response
        assertThat(response.getStatusCode())
                .as("Wrong code was sent in the response")
                .isEqualTo(422);
        assertThat(errors.getErrors().getUsernameError())
                .as("Wrong error message was sent")
                .isEqualTo(errorMessage);
        assertThat(errors.getErrors().getEmailError())
                .as("Wrong error message was sent")
                .isEqualTo(errorMessage);
    }

    private Stream<Arguments> unsuccessfulUserCreationData() {
        return Stream.of(
                Arguments.of("blank", "blank", "can't be blank"),
                Arguments.of("taken", "taken", "is already taken.")
        );
    }
}
