package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.GenerateAPIXml;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiStepsDefs extends BaseStepsDefs {

    static String authToken, response, endpoint;

    @Given("^System API is up and running$")
    public void systemAPIIsUpAndRunning()  {
        RestAssured.baseURI = "http://cssitcacapi01-dev.azurewebsites.net";
    }

    @When("^User sends sign in request with following data$")
    public void userSendsSignInRequestWithFollowingData(DataTable table) {
        response = given().log().all().contentType("text/xml").header("Accept", "text/xml").body(GenerateAPIXml.getLoginRequestBody(table)).when().post("/api/integration/login").asString();
    }

    @Then("^Access token is sent back by the system$")
    public void accessTokenIsSentBackByTheSystem() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
        Assert.assertTrue(xml.getList("Token").size() > 0);
    }


    @Then("^System responses with proper error '(.+)'$")
    public void systemResponsesWithProperErrorMessage(String message) {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Error", xml.get("Status").toString());
        Assert.assertTrue(xml.getList("Message").toString().contains(message));
    }
}
