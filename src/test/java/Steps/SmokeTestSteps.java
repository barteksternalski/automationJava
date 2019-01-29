package Steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import environment.driverFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.homePage;

import java.lang.management.ManagementFactory;

public class SmokeTestSteps {

    private WebDriver driver;
    private homePage _homePage;

    //@Before
    public void setupBrowser() {
        driver =  driverFactory.getDriver(driverFactory.DriverType.CHROME);
        _homePage = new homePage(driver, 10);
    }

    @Given("User opens Avanade page")
    public void openAvanadeMainPage() {
        driver.get("http://avanade.com");
    }

    @When("User navigates to '(.+)'")
    public void navigatesTo(String tab) {
        _homePage.gotoMainTab(tab);
    }

    @Then("Proper page title '(.+)' is displayed")
    public void verifyPageTitle(String title) {
        Assert.assertEquals("Wrong title: " + _homePage.getPageTitle(), title, _homePage.getPageTitle());
    }

    //@After
    public void tearDown() {
        driver.close();
    }

  @Given("Print {string} test from first scenario")
  public void printNameTestFromFirstScenario(String name) {
    long threadId = Thread.currentThread().getId();
    String processName = ManagementFactory.getRuntimeMXBean().getName();
    System.out.println("Started in thread: " + threadId + ", in JVM: " + processName + ", name: " + name);
  }

  @Given("Print {string} test from second scenario")
  public void printNameTestFromSecondScenario(String name) {
    long threadId = Thread.currentThread().getId();
    String processName = ManagementFactory.getRuntimeMXBean().getName();
    System.out.println("Started in thread: " + threadId + ", in JVM: " + processName + ", name: " + name);
  }
}
