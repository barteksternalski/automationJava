package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.Assert;
import pages.CreateSingleESlip;


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

    @Given("^User is creating new eEslip$")
    public void openNewEslipPage() {
        driver.get("http://cssitcacweb01-dev.azurewebsites.net/createsingle");
    }

    @When("^User creates new eSlip with given customer and policy information with given data$")
    public void fillCustomerInformation(DataTable table) {
        createSingleESlip.fillCustomerInformation(table);
        createSingleESlip.next();
    }


    @Given("^User is on main page$")
    public void userIsOnMainPage() {
        driver.get("http://cssitcacweb01-dev.azurewebsites.net/");
        loginPage.login("", "");
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
