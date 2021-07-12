package com.bb.recruitment.tests.apiscenarios;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


import com.bb.recruitment.datamodel.Errors;
import com.bb.recruitment.datamodel.User;
import com.bb.recruitment.datamodel.UserDetails;
import com.bb.recruitment.datamodel.UserDetailsView;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.bb.recruitment.utils.PropertyLoader;

public class BaseApiTest {

    static PropertyLoader propertyLoader = new PropertyLoader("application.properties");
    static String basicAuthUsername = propertyLoader.loadProperty("username");
    static String basicAuthPassword = propertyLoader.loadProperty("password");
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RestAssured.baseURI = propertyLoader.loadProperty("baseUri");
    }

    User generateDataForRegisteringNewUser() {
        String newUsername = "bbUser" + randomAlphabetic(5).toLowerCase();
        return new User(new UserDetails(
                newUsername,
                newUsername.concat("@gmail.com"),
                "Test123!"
        ));
    }

    Response registerNewUser(User newUser) throws IOException {

        String endpoint = propertyLoader.loadProperty("registerNewUser");

        return given()
                .filter(new AllureRestAssured())
                .auth().basic(basicAuthUsername, basicAuthPassword)
                .header("Content-Type", "application/json")
                .body(objectMapper.writerWithView(UserDetailsView.RegisterNewUser.class).writeValueAsString(newUser))
                .when()
                .post(endpoint);
    }

    User parseResponseUserData(String response) throws IOException {
        try {
            return objectMapper.readValue(response, new TypeReference<User>() {
            });
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }

    Errors parseResponseErrorsData(String response) throws IOException {
        try {
            return objectMapper.readValue(response, new TypeReference<Errors>() {
            });
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }

    //TODO: as the endpoint is not working as expected currently method is useless
    public User getRandomUser() throws IOException {
        RestAssured.baseURI = propertyLoader.loadProperty("baseUri");
        return parseResponseUserData(registerNewUser(generateDataForRegisteringNewUser()).asString());
    }
}
