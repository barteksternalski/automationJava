package apiscenarios;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;


import datamodel.Errors;
import datamodel.User;
import datamodel.UserDetails;
import datamodel.UserDetailsView;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import properties.PropertyLoader;

public class BaseApiTest {

    static String basicAuthUsername = new PropertyLoader("application.properties").loadProperty("username");
    static String basicAuthPassword = new PropertyLoader("application.properties").loadProperty("password");
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        RestAssured.baseURI = new PropertyLoader("application.properties").loadProperty("baseUri");
    }

    User generateDataForRegisteringNewUser() {
        String newUsername = "bbUser" + randomAlphabetic(5);
        return new User(new UserDetails(
                newUsername,
                newUsername.concat("@gmail.com"),
                "Test123!"
        ));
    }

    Response registerNewUser(User newUser) throws IOException {

        String endpoint = new PropertyLoader("application.properties").loadProperty("registerNewUser");

        return given()
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
}
