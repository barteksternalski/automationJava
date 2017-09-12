package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;


public class GeneralStepsDefs extends stepDefinitions.BaseStepsDefs {

    @Before
    public void setUp() {
        initDriver();
        initPages();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("^User is creating new eEslip$")
    public void openNewEslipPage() {
        driver.get("http://cssitcacweb01-dev.azurewebsites.net/createsingle");
    }

    @When("^User creates new eSlip with given customer and policy information with given data$")
    public void fillCustomerInformation(DataTable table) {
        createSingleESlip.fillCustomerInformation(table);
        createSingleESlip.saveDraft();
    }

    @Then("^ESlip draft is created$")
    public void verifyCreatedDraft() {
        System.out.println("banan");
    }

}
