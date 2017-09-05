package stepDefinitions;

import common.BaseSteps;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.LandingPage;


public class GeneralTests extends BaseSteps {

    @Before
    public void setUp() {
        initDriver();
        initPages();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("^Open url \"([^\"]*)\"$")
    public void openPageUrl(String url)  {
        driver.get(url);
        Assert.assertNotNull("URL is empty", url);
    }

    @When("^User navigates to eslips drafts$")
    public void userNavigatesToEslipsDrafts(){
        landingPage.navigateToDrafts();
    }

    @Then("^Page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        Assert.assertTrue(landingPage.verifyPageTitle(title));
    }
}
