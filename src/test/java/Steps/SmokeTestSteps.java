package Steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import environment.driverFactory;
import org.junit.Assert;
import pages.homePage;

public class SmokeTestSteps {

    private homePage _homePage;

    @Before
    public void setupBrowser() {
        driverFactory.setDriver("chrome");
        _homePage = new homePage(driverFactory.getDriver(), 10);
    }

    @After
    public void tearDown() {
        driverFactory.getDriver().close();
    }

    @Given("User opens Avanade page")
    public void openAvanadeMainPage() {
        driverFactory.getDriver().get("http://avanade.com");
    }

    @When("User navigates to '(.+)'")
    public void navigatesTo(String tab) {
        _homePage.gotoMainTab(tab);
    }

    @Then("Proper page title '(.+)' is displayed")
    public void verifyPageTitle(String title) {
        Assert.assertEquals("Wrong title: " + _homePage.getPageTitle(), title, _homePage.getPageTitle());
    }



}
