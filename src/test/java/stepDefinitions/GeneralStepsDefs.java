package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.Assert;

public class GeneralStepsDefs extends stepDefinitions.BaseStepsDefs {

    private static int noOfVehicles     = 0;
    private static int noOfBackTexts    = 0;
    private static int currentPosition  = 0;

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

    @When("^User clicks next$")
    public void userClicksNext() {
        createSingleESlip.next();
    }

    @Given("^User is creating new eEslip$")
    public void openNewEslipPage() {
        landingPage.navigateToSingleESlip();
    }

    @When("^User creates new eSlip with given customer and policy information with given data$")
    public void fillCustomerInformation(DataTable table) {
        landingPage.navigateToSingleESlip();
        createSingleESlip.fillCustomerInformation(table);
    }

    @When("^User saves eSlip draft$")
    public void userSavesESlipDraft() {
        createSingleESlip.saveDraft();
    }

    @Given("^User opens drafted '(.+)' eSlip$")
    public void userOpensDraftedNameESlip(String name) {
        landingPage.navigateToDrafts();
        listOfDrafts.selectESlipByName(name);
        listOfDrafts.editESlip();
    }

    @Then("^'(.+)' page is displayed$")
    public void verifyPageTitle(String title){
        createSingleESlip.verifyPageTitle(title);
    }

    @When("^User adds new vehicle with given data$")
    public void userAddsNewVehicleWithGivenData(DataTable table) {
        noOfVehicles = createSingleESlip.getNoOfVehiclesOnList();
        createSingleESlip.fillVehicleInformation(table);
    }

    @When("^User adds new back text section with given data$")
    public void userAddsNewBackTextSectionWithGivenData(DataTable table)  {
        noOfBackTexts = createSingleESlip.getNoOfBackTextsOnList();
        createSingleESlip.fillBackText(table);
        createSingleESlip.addBackTextEntry();
    }

    @Then("^Vehicle with '(.+)' is added to eSlip$")
    public void verifyNoOfVehiclesOnTheList(String vin) {
        Assert.assertTrue(createSingleESlip.verifyIfVehicleWithVinIsListed(vin));
    }

    @Then("^Back text with '(.+)' is added to eSlip$")
    public void verifyNoOfBackTextsOnTheList(String title) {

    }

    @When("^User edits '(.+)' vehicle with given data$")
    public void userEditsVehicleNoVehicleWithGivenData(String vehicleNo, DataTable table) {
        createSingleESlip.editVehicleInformation(vehicleNo, table);
    }

    @Then("^Vehicle info is updated$")
    public void vehicleInfoIsUpdated() {
        System.out.println("TO FILL !!!");
    }


    @Then("^ESlip '(.+)' is displayed on Drafts list$")
    public void eslipNameIsDisplayedOnDraftsList(String name) {
        landingPage.navigateToDrafts();
        Assert.assertTrue(listOfDrafts.verifyIfESlipInDisplayedOnList(name));
    }

    @When("^User moves up vehicle with '(.+)' vin number$")
    public void userMovesUpVehicleWithVinVinNumber(String vin) {
        currentPosition = createSingleESlip.getVehicleByVinPosition(vin);
        createSingleESlip.moveVehicleUp(vin);
    }

    @When("^User moves down vehicle with '(.+)' vin number$")
    public void userMovesDownVehicleWithVinVinNumber(String vin) {
        currentPosition = createSingleESlip.getVehicleByVinPosition(vin);
        createSingleESlip.moveVehicleDown(vin);
    }

    @Then("^Vehicle with '(.+)' is reordered$")
    public void vehicleOrderIsChanged(String vin) {
        Assert.assertNotEquals(currentPosition, createSingleESlip.getVehicleByVinPosition(vin));
    }

    @When("^User removes vehicle with '(.+)' vin number$")
    public void userRemovesVehicleWithTemp(String vin)  {
        createSingleESlip.removeVehicle(vin);
    }

    @Then("^Vehicle with '(.+)' vin number is removed from eSlip$")
    public void vehicleIsRemovedFromESlip(String vin) {
        Assert.assertFalse(createSingleESlip.verifyIfVehicleWithVinIsListed(vin));
    }

    @When("^User moves up back text with '(.+)' title$")
    public void userMovesUpBackTextWithTempTitle(String title) {
        currentPosition = createSingleESlip.getBackTextByTitlePosition(title);
        createSingleESlip.moveBackTextUp(title);
    }

    @When("^User moves down back text with '(.+)' title$")
    public void userMovesDownBackTextWithTempTitle(String title) {
        currentPosition = createSingleESlip.getBackTextByTitlePosition(title);
        createSingleESlip.moveBackTextDown(title);
    }

    @Then("^Back text with '(.+)' title is reordered$")
    public void backTextWithTempTitleIsReordered(String title) {
        Assert.assertNotEquals(currentPosition, createSingleESlip.getBackTextByTitlePosition(title));
    }

    @When("^User removes back text with '(.+)' title$")
    public void userRemovesBackTextWithTempTitle(String title) {
        createSingleESlip.removeBackText(title);
    }

    @Then("^Back text with '(.+)' is removed from eSlip$")
    public void backTextWithTempIsRemovedFromESlip(String title) {
        Assert.assertFalse(createSingleESlip.verifyIfBackTextWithTitleIsListed(title));
    }
}
