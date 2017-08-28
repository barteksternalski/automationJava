import com.jayway.restassured.RestAssured;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import helpers.dataHelper;
import helpers.dbConnection;
import helpers.fbConnection;
import helpers.propertyLoader;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class smokeTest {

    String envSysProp = System.getProperty("env");

    static HashMap<String, Object> map = new HashMap<>();
    static String baseURI;
    static String password = "Test1234";
    static String authToken, response, endpoint;
    static String collectionId, designateId, assetId;
    static String generatedUName, generatedDName, generatedUPhone, generatedDPhone, generatedFBName, generatedFBPhone;
    static String dbName, dbPath, dbUser, dbPass;

    @BeforeSuite
    public void setupData() {
        envSysProp = "us_stg";

        generatedUName = "mlApiU" + dataHelper.generateDayAndHour();
        generatedUPhone = dataHelper.generatePhone(envSysProp);
        generatedDName = "mlApiD" + dataHelper.generateDayAndHour();
        generatedDPhone = dataHelper.generatePhone(envSysProp);
        generatedFBName = "mlApiFB" + dataHelper.generateDayAndHour();
        generatedFBPhone = dataHelper.generatePhone(envSysProp);

        System.out.println("User: " + generatedUName + "\nphone: " + generatedUPhone);
        System.out.println("Designate: " + generatedDName + "\nphone: " + generatedDPhone);
        System.out.println("FB User: " + generatedFBName + "\nphone: " + generatedFBPhone);

        baseURI = new propertyLoader("application.properties").loadProperty(envSysProp);
        dbName = new propertyLoader("application.properties").loadProperty(envSysProp.concat("_dbName"));
        dbPath = new propertyLoader("application.properties").loadProperty(envSysProp.concat("_dbPath"));
        dbUser = new propertyLoader("application.properties").loadProperty(envSysProp.concat("_dbUser"));
        dbPass = new propertyLoader("application.properties").loadProperty(envSysProp.concat("_dbPass"));

    }

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = baseURI;
    }

    @Test(description = "Create new user", dependsOnMethods = {})
    public void createUser() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Email", generatedUName+"@mailinator.com");
        map = dataHelper.putIntoMap("Password", password);
        map = dataHelper.putIntoMap("FirstName", "apiU");
        map = dataHelper.putIntoMap("LastName", "apiU");
        map = dataHelper.putIntoMap("BirthDate", "05-1975");
        map = dataHelper.putIntoMap("Phone", generatedUPhone);
        endpoint = new propertyLoader("application.properties").loadProperty("userSignUp");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Activate user via DB", dependsOnMethods = {"createUser"})
    public void activateUser() throws Exception {
        dbConnection dbConn = new dbConnection();
        String sQuery = "select * from dbo.[AccessToken] where Id=(select ActivationTokenId from dbo.[User] where Email='" + generatedUName + "@mailinator.com" + "')";
        String sActToken = dbConn.getActivationToken(dbConn.executeQuery(sQuery, dbName, dbPath, dbUser, dbPass), "Value");

        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("token", sActToken);
        endpoint = new propertyLoader("application.properties").loadProperty("userActivate");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Login as a user", dependsOnMethods = {"activateUser"})
    public void loginAsAUser() throws Exception {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Email", generatedUName + "@mailinator.com");
        map = dataHelper.putIntoMap("Password", password);
        endpoint = new propertyLoader("application.properties").loadProperty("userSignIn");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        authToken = given().queryParams(map).when().post(endpoint).headers().getValue("Set-Cookie");
        authToken = authToken.replace("path=/; HttpOnly","");



        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(response);
        ByteArrayInputStream input =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);

        Element root = doc.getDocumentElement();
        root.getAttribute("c:kokos");
    }

    @Test(description = "Check user details", dependsOnMethods = {"loginAsAUser"})
    public void getUserDetails() {
        endpoint = new propertyLoader("application.properties").loadProperty("userDetails");
        response = given().log().all().cookie(authToken).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Create new collection", dependsOnMethods = {"loginAsAUser"})
    public void createCollection() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Description", "newCollection");
        map = dataHelper.putIntoMap("Name", "321");
        map = dataHelper.putIntoMap("ReminderPeriodId", "3");
        map = dataHelper.putIntoMap("Scheduled", "false");
        endpoint = new propertyLoader("application.properties").loadProperty("createCollection");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        JSONObject jobj = new JSONObject(response);
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        collectionId = jobj.getJSONObject("Data").get("Id").toString();

    }

    @Test(description = "Tag created collection", dependsOnMethods = {"createCollection"})
    public void tagCollection() {
        endpoint = new propertyLoader("application.properties").loadProperty("getTagsList");
        response = given().log().all().cookie(authToken).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        JSONObject jobj = new JSONObject(response);
        List<Object> tagList = dataHelper.getListOfElements(jobj.getJSONArray("Data"), "Id");
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Name", "321");
        map = dataHelper.putIntoMap("Id", collectionId);
        map = dataHelper.putIntoMap("TagIds", tagList.get(new Random().nextInt(tagList.size())));
        endpoint = new propertyLoader("application.properties").loadProperty("editCollection");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Add new file", dependsOnMethods = {"tagCollection"})
    public void addNewFile() throws Exception {
        File file = new File("src/test/resources/helpFiles/challenge.jpg");

        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Description", "temp file");
        map = dataHelper.putIntoMap("FileName", "challenge.jpg");
        map = dataHelper.putIntoMap("FileSizeInBytes", file.length());
        map = dataHelper.putIntoMap("PacketIds", collectionId);
        endpoint = new propertyLoader("application.properties").loadProperty("addAsset");
        response = given().log().all().multiPart("files[]", file, "image/jpeg").cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        JSONObject jobj = new JSONObject(response);
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        assetId = jobj.getJSONObject("Data").get("Id").toString();
        //upload file to Azure
        CloudBlockBlob blob = new CloudBlockBlob(new URI(jobj.getJSONObject("Data").get("Uri").toString()));
        blob.uploadFromFile("src/test/resources/helpFiles/challenge.jpg");
    }

    @Test(description = "Upload file to server", dependsOnMethods = {"addNewFile"})
    public void uploadFile() {
        File file = new File("src/test/resources/helpFiles/challenge.jpg");
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("id", assetId);
        endpoint = new propertyLoader("application.properties").loadProperty("uploadAsset");
        response = given().log().all().multiPart("files[]", file, "image/jpeg").cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Add file to collection", dependsOnMethods = {"uploadFile"})
    public void addFileToCollection() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Name", "321");
        map = dataHelper.putIntoMap("Id", collectionId);
        map = dataHelper.putIntoMap("AssetIds", assetId);
        map = dataHelper.putIntoMap("Template", "false");
        map = dataHelper.putIntoMap("TagIds", "6");
        endpoint = new propertyLoader("application.properties").loadProperty("editCollection");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Create new designate", dependsOnMethods = {"loginAsAUser"})
    public void addDesignate() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Description", "java junitSmokeTest");
        map = dataHelper.putIntoMap("Email", generatedDName + "@mailinator.com");
        map = dataHelper.putIntoMap("FirstName", "banan");
        map = dataHelper.putIntoMap("LastName", "banan");
        map = dataHelper.putIntoMap("Trusted", "false");
        endpoint = new propertyLoader("application.properties").loadProperty("addDesignate");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        JSONObject jobj = new JSONObject(response);
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        designateId = jobj.getJSONObject("Data").get("Id").toString();
    }


    @Test(description = "Make designate trusted", dependsOnMethods = {"addDesignate"})
    public void editDesignate() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Description", "java junitSmokeTest");
        map = dataHelper.putIntoMap("Email", generatedDName + "@mailinator.com");
        map = dataHelper.putIntoMap("FirstName", "banan");
        map = dataHelper.putIntoMap("LastName", "banan");
        map = dataHelper.putIntoMap("Id", designateId);
        map = dataHelper.putIntoMap("Trusted", "true");
        endpoint = new propertyLoader("application.properties").loadProperty("updateDesignate");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Assign designate to collection",  dependsOnMethods = {"addDesignate", "addFileToCollection"})
    public void addDesignateToCollection() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Name", "321");
        map = dataHelper.putIntoMap("Id", collectionId);
        map = dataHelper.putIntoMap("AssetIds", assetId);
        map = dataHelper.putIntoMap("Template", "false");
        map = dataHelper.putIntoMap("TagIds", "6");
        map = dataHelper.putIntoMap("RecipientIds", designateId);
        endpoint = new propertyLoader("application.properties").loadProperty("editCollection");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Share collection", dependsOnMethods = {"addDesignateToCollection"})
    public void shareCollection() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Name", "321");
        map = dataHelper.putIntoMap("Id", collectionId);
        map = dataHelper.putIntoMap("AssetIds", assetId);
        map = dataHelper.putIntoMap("TagIds", "6");
        map = dataHelper.putIntoMap("SendOn","03/11/2016");
        map = dataHelper.putIntoMap("RecipientIds", designateId);
        endpoint = new propertyLoader("application.properties").loadProperty("editCollection");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Logout as a user",  dependsOnMethods = {"addDesignateToCollection"})
    public void userLogout() {
        endpoint = new propertyLoader("application.properties").loadProperty("userSignOut");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Create new designate",  dependsOnMethods = {"userLogout"})
    public void createDesignate() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Email", generatedDName+"@mailinator.com");
        map = dataHelper.putIntoMap("Password", password);
        map = dataHelper.putIntoMap("FirstName", "apiD");
        map = dataHelper.putIntoMap("LastName", "apiD");
        map = dataHelper.putIntoMap("BirthDate", "05-1975");
        map = dataHelper.putIntoMap("Phone", generatedDPhone);
        endpoint = new propertyLoader("application.properties").loadProperty("userSignUp");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Activate designate via DB", dependsOnMethods = {"createDesignate"})
    public void activateDesignate() throws Exception {
        dbConnection dbConn = new dbConnection();
        String sQuery = "select * from dbo.[AccessToken] where Id=(select ActivationTokenId from dbo.[User] where Email='" + generatedDName + "@mailinator.com" + "')";
        String sActToken = dbConn.getActivationToken(dbConn.executeQuery(sQuery, dbName, dbPath, dbUser, dbPass), "Value");

        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("token", sActToken);
        endpoint = new propertyLoader("application.properties").loadProperty("userActivate");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Login as a designate", dependsOnMethods = {"activateDesignate"})
    public void loginAsADesignate() {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Email", generatedDName + "@mailinator.com");
        map = dataHelper.putIntoMap("Password", password);
        endpoint = new propertyLoader("application.properties").loadProperty("userSignIn");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        authToken = given().log().all().queryParams(map).when().post(endpoint).headers().getValue("Set-Cookie");
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        authToken = authToken.replace("path=/; HttpOnly","");
    }

    @Test(description = "Accept designate invitation", dependsOnMethods = {"loginAsADesignate"})
    public void acceptDesignateInvitation() throws Exception {
        Thread.sleep(5000);
        dbConnection dbConn = new dbConnection();
        String sQuery = "select * from dbo.[AccessToken] where Id = (select AcceptTokenId from dbo.[Recipient] where Email='" + generatedDName + "@mailinator.com" + "')";
        String sActToken = dbConn.getActivationToken(dbConn.executeQuery(sQuery, dbName, dbPath, dbUser, dbPass), "Value");

        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("token", sActToken);
        endpoint = new propertyLoader("application.properties").loadProperty("designateAccept");
        response = given().log().all().cookie(authToken).queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Accept trust invitation", dependsOnMethods = {"acceptDesignateInvitation"})
    public void acceptTrustInvitation() throws Exception {
        Thread.sleep(10000);
        dbConnection dbConn = new dbConnection();
        String sQuery = "select * from dbo.[AccessToken] where Id=(select TrustAcceptTokenId from dbo.[Recipient] where Email='" + generatedDName + "@mailinator.com" + "')";
        String sActToken = dbConn.getActivationToken(dbConn.executeQuery(sQuery, dbName, dbPath, dbUser, dbPass), "Value");
        System.out.println("Token: " + sActToken);

        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("token", sActToken);
        endpoint = new propertyLoader("application.properties").loadProperty("trustedAccept");
        response = given().log().all().cookie(authToken).queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Accept share collection invitation", dependsOnMethods = {"acceptDesignateInvitation", "shareCollection"})
    public void acceptCollection() throws Exception {
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Id", collectionId);
        endpoint = new propertyLoader("application.properties").loadProperty("receiveCollection");
        response = given().log().all().cookie(authToken).queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
    }

    @Test(description = "Verify FB connection", dependsOnMethods = {"acceptTrustInvitation"})
    public void activateUserViaFB() throws Exception {

        fbConnection fbConn = new fbConnection();

        // create new FB Test User
        RestAssured.baseURI = "https://graph.facebook.com";
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("email", generatedFBName+"@mailinator.com");
        map = dataHelper.putIntoMap("installed", "true");
        map = dataHelper.putIntoMap("name", "Facetest User");
        map = dataHelper.putIntoMap("access_token", fbConn.getFBAccessToken());
        response = given().log().all().queryParams(map).when().post("/707945306015098/accounts/test-users").asString();
        System.out.println(response);

        //fetch needed data
        JSONObject jobj = new JSONObject(response);
        String fbId     = jobj.get("id").toString();
        String fbEmail  = jobj.get("email").toString();
        String fbToken  = jobj.get("access_token").toString();

        //create new account based on created FB test user
        RestAssured.baseURI = baseURI;
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("Email", fbEmail);
        map = dataHelper.putIntoMap("Password", password);
        map = dataHelper.putIntoMap("FirstName", "apiFB");
        map = dataHelper.putIntoMap("LastName", "apiFB");
        map = dataHelper.putIntoMap("BirthDate", "05-1975");
        map = dataHelper.putIntoMap("FacebookAccessToken", fbToken);
        map = dataHelper.putIntoMap("Phone", generatedFBPhone);
        endpoint = new propertyLoader("application.properties").loadProperty("userSignUp");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");

        //login as a FB user
        dataHelper.clearMap(map);
        map = dataHelper.putIntoMap("FacebookAccessToken", fbToken);
        endpoint = new propertyLoader("application.properties").loadProperty("userExternalSignIn");
        response = given().log().all().queryParams(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");
        authToken = given().queryParams(map).when().post(endpoint).headers().getValue("Set-Cookie");
        authToken = authToken.replace("path=/; HttpOnly","");

        //logout as a FB user
        endpoint = new propertyLoader("application.properties").loadProperty("userSignOut");
        response = given().log().all().cookie(authToken).queryParameters(map).when().post(endpoint).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "Success", true), "\nJSON Response:\n" + response + "\n");

        //delete created user
        dataHelper.clearMap(map);
        RestAssured.baseURI = "https://graph.facebook.com";
        response = given().log().all().queryParam("uid", fbId).queryParam("access_token", fbConn.getFBAccessToken()).when().delete("/" + fbId).asString();
        Assert.assertTrue(dataHelper.verifyMessage(response, "success", true), "\nJSON Response:\n" + response + "\n");
    }

}
