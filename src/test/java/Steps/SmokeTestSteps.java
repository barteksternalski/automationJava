package Steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.Rule;
import pages.*;
import java.util.ResourceBundle;

import static com.codeborne.selenide.Selenide.*;

public class SmokeTestSteps {

    private static ResourceBundle rb = ResourceBundle.getBundle("config");

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void setUpEnvironment() {
        Configuration.remote = rb.getString("selenide.remoteURL");
        Configuration.browser = rb.getString("selenide.browser");
        Configuration.startMaximized = rb.getObject("selenide.isMaximized").equals("true");
        Configuration.reportsFolder = rb.getString("selenide.screenshotsFolder");
    }

    @Given("User opens Google page")
    public void userOpensGooglePage() {
        open("http://google.com");
    }

    @When("User searches for '(.+)'")
    public void userSearchesForSelenide(String searchFor) {
        new GoogleSearchPage().searchFor(searchFor);
    }

    @Then("Proper result '(.+)' is displayed")
    public void properResultsAreDisplayed(String result) {
        GoogleResultsPage results = new GoogleResultsPage();
        results.checkResultsSizeIsAtLeast(1);
        results.checkResultHasTest(0, result);
    }
}
