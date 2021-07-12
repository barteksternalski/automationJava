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
import com.bb.recruitment.pages.ProfilePage;
import com.bb.recruitment.pages.SettingsPage;

@Execution(ExecutionMode.CONCURRENT)
@Feature("User settings tests suite")
public class UserSettingsTest extends BaseUiTest {

    @Test
    @Tag(UI_TESTS)
    @DisplayName("Verify you can modify user settings")
    public void modifyUserSettings() {

        //sign in with created user
        HomePage homePage = registerNewUser();

        //open settings page
        SettingsPage settingsPage = homePage.openSettingsPage();

        //modify user settings
        String newUsername = "dude" + randomAlphabetic(5).toLowerCase();
        String newBio = "awesome bio";
        ProfilePage profilePage = settingsPage.setUsernameValue(newUsername)
                .setBioValue(newBio)
                .updateSettings();

        //verify user data was updated properly
        assertThat(profilePage.getProfileData())
                .as("Profile data is incorrect")
                .contains(newUsername)
                .contains(newBio);
    }

}
