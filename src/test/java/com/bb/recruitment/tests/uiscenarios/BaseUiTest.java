package com.bb.recruitment.tests.uiscenarios;

import static com.codeborne.selenide.Selenide.open;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ResourceBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import com.bb.recruitment.pages.HomePage;
import com.bb.recruitment.utils.PropertyLoader;
import com.codeborne.selenide.AuthenticationType;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseUiTest {

    private static final ResourceBundle selenideRB = ResourceBundle.getBundle("config");
    static PropertyLoader propertyLoader = new PropertyLoader("application.properties");

    @BeforeAll
    void setUpEnvironment() {
//        Configuration.remote = selenideRB.getString("selenide.remoteURL");
        Configuration.browser = selenideRB.getString("selenide.browser");
        Configuration.headless = selenideRB.getObject("selenide.headless").equals("true");
        Configuration.startMaximized = selenideRB.getObject("selenide.isMaximized").equals("true");
        Configuration.screenshots = selenideRB.getObject("selenide.screenshots").equals("true");
        Configuration.reportsFolder = selenideRB.getString("selenide.screenshotsFolder");
    }

    @BeforeEach
    void mainPageWeirdAuthentication() {
        String username = propertyLoader.loadProperty("username");
        String password = propertyLoader.loadProperty("password");
        open(propertyLoader.loadProperty("baseUri"), AuthenticationType.BASIC, username, password);
    }

    @AfterEach
    void tearDown() {
        if (isWebDriverStarted()) {
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
        }
    }

    HomePage registerNewUser() {
        String newUsername = "bbUser" + randomAlphabetic(5);
        return new HomePage().openSignUpPage()
                .setUsernameValue(newUsername)
                .setEmailValue(newUsername.concat("@yahoo.com"))
                .setPasswordValue("Test123!")
                .signUp();
    }

    protected static boolean isWebDriverStarted() {
        try {
            return nonNull(WebDriverRunner.getWebDriver());
        } catch (Exception e) {
            return false;
        }
    }

}
