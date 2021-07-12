package com.bb.recruitment.tests.uiscenarios;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static com.bb.recruitment.tags.SuiteNames.UI_TESTS;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import io.qameta.allure.Feature;
import com.bb.recruitment.pages.HomePage;

@Execution(ExecutionMode.CONCURRENT)
@Feature("User registration tests suite")
public class UserRegistrationTest extends BaseUiTest {

    @Test
    @Tag(UI_TESTS)
    @DisplayName("Verify you can register new user")
    public void verifyNewUserRegistration() {

        //register new user
        String newUsername = "bbuser" + randomAlphabetic(5).toLowerCase();
        HomePage homePage = new HomePage().openSignUpPage()
                .setUsernameValue(newUsername)
                .setEmailValue(newUsername.concat("@yahoo.com"))
                .setPasswordValue("Test123!")
                .signUp();

        //verify your feed is selected on the home page
        assertThat(homePage.getActiveFeedTab())
                .as("Wrong feed tab is selected")
                .isEqualTo("Your Feed");

        //verify settings button is visible
        assertThat(homePage.isSettingsButtonVisible())
                .as("Settings button is not visible")
                .isTrue();

        //verify profile link contains proper user name
        assertThat(homePage.getLoggedUserLinkLabel())
                .as("Incorrect user name in displayed in profile link label")
                .isEqualTo(newUsername);
    }

}
