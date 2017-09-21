package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.Assert;

public class GeneralStepsDefs extends stepDefinitions.BaseStepsDefs {

    @Given("^Setup browser$")
    public void setupBrowser() {
        initDriver();
        initPages();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Given("^Close browser$")
    public void closeBrowser() {
        driver.quit();
    }

    @Given("^User is on login page$")
    public void userIsOnLoginPage() {
        driver.get("http://cssitcacweb01-dev.azurewebsites.net/");
    }

    @When("^User enters '(.+)' and '(.+)'$")
    public void userEntersLoginAndPassword(String user, String pass)  {
        loginPage.login(user, pass);
    }

    @Then("^Main page is displayed$")
    public void mainPageIsDisplayed()  {
        Assert.assertTrue(landingPage.verifyMainPageIsDisplayed());
    }

    @Given("^User is on dashboard page$")
    public void userIsOnMainPage() {
        landingPage.navigateToDashboard();
    }

    @When("^User creates new user with given data$")
    public void userCreatesNewUserWithGivenData(DataTable table) throws Exception {
        landingPage.navigateToCreateUser();
        createUser.createNewUser(table);
    }

    @Then("^User '(.+)' is created$")
    public void userIsCreated(String user) {
        landingPage.navigateToUserList();
        Assert.assertTrue(listOfUsers.userVisibleOnUserList(user));
    }

    @Then("^Proper error message '(.+)' is displayed$")
    public void verifyErrorMessage(String message) {
        Assert.assertTrue(createSingleESlip.verifyWarningMessage(message));
    }

    @Given("^User is on user listing page$")
    public void userIsOnUserListingPage() {
        landingPage.navigateToUserList();
    }

    @When("^User activates selected '(.+)'$")
    public void userActivatesSelectedUser(String user)  {
        listOfUsers.selectUserByName(user);
        listOfUsers.activateUser();
    }

    @Then("^'(.+)' user is activated$")
    public void userIsActivated(String user)  {
        Assert.assertTrue(listOfUsers.verifyUserActivationStatus(user));
    }

    @When("^User deactivates selected '(.+)'$")
    public void userDeactivatesSelectedUser(String user){
        listOfUsers.selectUserByName(user);
        listOfUsers.deactivateUser();
    }

    @Then("^'(.+)' user is deactivated$")
    public void userIsDeactivated(String user)  {
        Assert.assertFalse(listOfUsers.verifyUserActivationStatus(user));
    }







    @Given("^User is creating new eEslip$")
    public void openNewEslipPage() {
        landingPage.navigateToSingleESlip();
    }

    @When("^User creates new eSlip with given customer and policy information with given data$")
    public void fillCustomerInformation(DataTable table) {
        landingPage.navigateToSingleESlip();
        createSingleESlip.fillCustomerInformation(table);
        createSingleESlip.next();
    }



    @Then("^'(.+)' page is displayed$")
    public void verifyPageTitle(String title){
        createSingleESlip.verifyPageTitle(title);
    }

    @When("^User adds new vehicle with given data$")
    public void userAddsNewVehicleWithGivenData(DataTable table) {
        createSingleESlip.fillVehicleInformation(table);
        createSingleESlip.next();
    }

    @When("^User adds new back text section with given data$")
    public void userAddsNewBackTextSectionWithGivenData(DataTable table)  {
        createSingleESlip.fillBackText(table);
        createSingleESlip.next();
    }
}
