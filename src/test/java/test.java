import environment.driverFactory;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.contactUsPage;
import pages.homePage;
import pages.searchResultsPage;

import java.text.MessageFormat;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test {

    private WebDriver driver;
    private homePage _homePage;
    private searchResultsPage _searchResultsPage;
    private contactUsPage _contactUsPage;

    @Before
    public void setupBrowser() {
        driver =  driverFactory.getDriver(driverFactory.DriverType.CHROME);
        _homePage = new homePage(driver, 10);
        _searchResultsPage = new searchResultsPage(driver, 10);
        _contactUsPage = new contactUsPage(driver, 10);
    }

    @Test
    public void test_01_navigation() {
        Map<String, String> mainNavigation = Map.ofEntries
                (
                        Map.entry( "Solutions", "Digital Business Solutions & Cloud Services | Avanade"),
                        Map.entry( "Technologies and Capabilities", "Technologies & Capabilities | Avanade" ),
                        Map.entry( "Client Stories", "Client Stories | Avanade" ),
                        Map.entry( "Thinking", "Thinking - Business Issues and Insights | Avanade" ),
                        Map.entry( "Careers", "Careers in Business Technology & IT | Avanade"),
                        Map.entry( "About Avanade", "About Avanade | Avanade" )
                );
        driver.get("http://avanade.com");
        mainNavigation.forEach((key, value) -> {
            _homePage.gotoMainTab(key);
            Assert.assertEquals(value, _homePage.getPageTitle());
        });
    }

    @Test
    public void test_02_search() {
        List<String> myList = Arrays.asList("qa", "devops", "avanade", "qwertyuiop");

        for (String field : myList) {
            driver.get("http://avanade.com");
            System.out.println(MessageFormat.format("Searching for: {0}", field));

            _homePage.searchForGivenTopic(field);
            System.out.println(MessageFormat.format("Avanade: {0}", _searchResultsPage.getNumberOfResults("Avanade")));
            System.out.println(MessageFormat.format("Blog: {0}", _searchResultsPage.getNumberOfResults("Blog")));
        }
    }

    @Test
    public void test_03_contactUs() {
        driver.get("http://avanade.com");
        _homePage.goToContactUs();
        Assert.assertTrue(_contactUsPage.isButtonActive("submit"));

        _contactUsPage.sendKeysToInputField("Email", "qwertyuiiop");
        Assert.assertFalse(_contactUsPage.isButtonActive("submit"));

        _contactUsPage.sendKeysToInputField("Email", "sadamczyk@avanade.com");
        Assert.assertTrue(_contactUsPage.isButtonActive("submit"));


        Map<String, String> formData = Map.ofEntries
        (
            Map.entry( "FirstName", "Sebastian"),
            Map.entry( "LastName", "Adamczyk" ),
            Map.entry( "Email", "sebastian.adamczyk@avanade.com" ),
            Map.entry( "Country", "Poland" ),
            Map.entry( "Company", "Avanade"),
            Map.entry( "Avanade_Functional_Role", "Information Technology" ),
            Map.entry( "Avanade_Job_Role", "Middle Management" ),
            Map.entry( "Avanade_Relationship", "Employee - Current or Former" ),
            Map.entry( "Avanade_Website_ContactUs_Request", "selnium test" )
        );

        driver.get("http://avanade.com");
        _homePage.goToContactUs();
        _homePage.acceptCookies();
        formData.forEach((key, value) -> {
            _contactUsPage.clickButton("submit");
            Assert.assertNotNull(_contactUsPage.getErrorMessageForGivenField(key));
            System.out.println(MessageFormat.format("Error message for: ''{0}'': ''{1}''", key, _contactUsPage.getErrorMessageForGivenField(key)));
            _contactUsPage.fillFormField(key, value);
        });

    }

    @After
    public void tearDown() {
        driver.close();
    }

}
