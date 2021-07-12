package com.bb.recruitment.tests.apiscenarios;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static com.bb.recruitment.tags.SuiteNames.API_TESTS;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import com.bb.recruitment.datamodel.User;
import com.bb.recruitment.datamodel.UserDetailsView;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

@Feature("Verification of endpoint for user settings")
@Execution(ExecutionMode.CONCURRENT)
public class UserSettingsTest extends BaseApiTest {

    @Test
    @Tag(API_TESTS)
    @DisplayName("Verify user can modify settings via api")
    void verifyUserCanModifySettings() throws IOException {

        //create new user
        User user = parseResponseUserData(registerNewUser(generateDataForRegisteringNewUser()).asString());
        String jwtToken = "Token ".concat(user.getUser().getToken());

        //modify user data
        String imageUrl = "https://i.stack.imgur.com/xHWG8.jpg";
        user.getUser().setImage(imageUrl);

        //send request to update user settings
        String endpoint = propertyLoader.loadProperty("currentUser");
        Response response = given()
                .filter(new AllureRestAssured())
                .auth().basic(basicAuthUsername, basicAuthPassword)
                .header("jwtauthorization", jwtToken)
                .header("Content-Type", "application/json")
                .body(objectMapper.writerWithView(UserDetailsView.SettingsFields.class).writeValueAsString(user))
                .when()
                .put(endpoint);

        //verify request was successful
        assertThat(response.getStatusCode())
                .as("There was something wrong with setting update")
                .isEqualTo(200);

        //verify user settings were updated correctly
        response = given()
                .filter(new AllureRestAssured())
                .auth().basic(basicAuthUsername, basicAuthPassword)
                .header("jwtauthorization", jwtToken)
                .when()
                .get(endpoint);

        assertThat(parseResponseUserData(response.asString()).getUser().getImage())
                .as("Retrieved data is incorrect")
                .isEqualTo(imageUrl);
    }

}
