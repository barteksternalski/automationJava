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

    private static String authToken, response;
    private static String sessionGUID, userGUID, messageGUID;
    private static XmlPath messagesList;

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
        authToken = xml.get("Token").toString();
        Assert.assertTrue(xml.getList("Token").size() > 0);
    }

    @Then("^System responses with proper error '(.+)'$")
    public void systemResponsesWithProperErrorMessage(String message) {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Error", xml.get("Status").toString());
        Assert.assertTrue(xml.getList("Message").toString().contains(message));
    }


    @When("^User sends eSlip creation request with following data$")
    public void userSendsESlipCreationRequestWithFollowingData(DataTable table)  {
        response = given().log().all().contentType("text/xml").header("Accept", "text/xml").header("Authorization", "Bearer ".concat(authToken)).body(GenerateAPIXml.getCreateESlipRequestBody(table)).when().post("/api/integration/eslip").asString();
        System.out.println(response);
    }

    @Then("^ESlip is properly created in the system$")
    public void eElipIsProperlyCreatedInTheSystem()  {
        XmlPath xml = new XmlPath(response).setRoot("eSlipRs");
        Assert.assertEquals("Success", xml.get("MsgStatus.MsgStatusCd").toString());
    }

    @Given("^CsioNET API is up and running$")
    public void csioNetAPIIsUpAndRunning() {
        RestAssured.baseURI = "https://qa-web.csionet.com";
    }

    @When("^User sends sign in request to CsioNET with following data$")
    public void userSendsSignInRequestToCsioNETWithFollowingData(DataTable table) {
        response = given().log().all().contentType("application/xml").body(GenerateAPIXml.getCsioNETLoginRequestBody(table)).when().post("/v1/MessageServices.aspx").asString();
    }

    @Then("^CsioNET system responses with proper error '(.+)'$")
    public void csioNetSystemResponsesWithProperErrorMessage(String message) {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Failed", xml.get("Status").toString());
        Assert.assertTrue(xml.getList("Exception").toString().contains(message));
    }

    @Then("^CsioNET sessionID is sent back by the system$")
    public void csioNetSessionIDIsSentBackByTheSystem() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
        sessionGUID = xml.get("SessionGUID").toString();
        userGUID = xml.get("UserGUID").toString();
    }

    @When("^User logs out from CsioNET$")
    public void userLogsOutFromCsioNET() {
        response = given().log().all().contentType("application/xml").body(GenerateAPIXml.getCsioNETLogoutRequestBody(sessionGUID)).when().post("/v1/MessageServices.aspx").asString();
    }

    @Then("^CsioNET sessionID is closed$")
    public void csioNetSessionIDIsClosed() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
    }

    @When("^Users sends request to get CsioNET messages with following data$")
    public void usersSendsRequestToGetCsioNETMessagesWithFollowingData(DataTable table) {
        response = given().log().all().contentType("application/xml").body(GenerateAPIXml.getCsioNETListOfMessagesBody(sessionGUID, userGUID, table)).when().post("/v1/MessageServices.aspx").asString();
    }

    @Then("^List of CsioNET messages is sent$")
    public void listOfCsioNETMessagesIsSent() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
        Assert.assertTrue(xml.getList("Message").size() > 0);
        messagesList = new XmlPath(response).setRoot("Response");
    }

    @When("^User sends request to get '(.+)' message from obtained list$")
    public void userSendsRequestToGetNoMessageFromObtainedList(String itemNumber){
        messageGUID = messagesList.getList("Message.MessageGUID").get(Integer.parseInt(itemNumber)).toString();
        response = given().log().all().contentType("application/xml").body(GenerateAPIXml.getCsioNETSingleMessagesBody(sessionGUID, userGUID, messageGUID)).when().post("/v1/MessageServices.aspx").asString();
    }

    @Then("^Message details are sent by CsioNET system$")
    public void messageDetailsAreSentByCsioNETSystem() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
    }

    @When("^User sends notification message to CsioNET with following data$")
    public void userSendsNotificationMessageToCsioNETWithFollowingData(DataTable table) {
        response = given().log().all().contentType("application/xml").body(GenerateAPIXml.getCsioNetSendMessageBody(sessionGUID, userGUID, table)).when().post("/v1/MessageServices.aspx").asString();
    }

    @Then("^Message is successfully delivered to CsioNET system$")
    public void messageIsSuccessfullyDeliveredToCsioNETSystem() {
        XmlPath xml = new XmlPath(response).setRoot("Response");
        Assert.assertEquals("Success", xml.get("Status").toString());
    }
}
