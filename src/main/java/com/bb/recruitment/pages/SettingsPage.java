package com.bb.recruitment.pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;
import static com.bb.recruitment.utils.WaitUtils.waitForConditionToBeFulfilled;

import com.bb.recruitment.utils.WaitUtils;
import com.codeborne.selenide.SelenideElement;

public class SettingsPage extends PageObject<SettingsPage> {

    private final SelenideElement pictureUrlInput = $(xpath("//*[@formcontrolname='image']"));
    private final SelenideElement usernameInput = $(xpath("//*[@formcontrolname='username']"));
    private final SelenideElement bioTextarea = $(xpath("//*[@formcontrolname='bio']"));
    private final SelenideElement emailInput = $(xpath("//*[@formcontrolname='email']"));
    private final SelenideElement passwordInput = $(xpath("//*[@formcontrolname='password']"));
    private final SelenideElement updateSettingsButton = $(xpath("//button[normalize-space(text())='Update Settings']"));

    @Override
    protected void waitToBeLoaded() {
        WaitUtils.waitForConditionToBeFulfilled(() -> getCurrentURL().contains("settings"), 5000L);
        $(xpath("//*[normalize-space(text())='Your Settings']")).shouldBe(visible);
    }

    @Override
    public SettingsPage returnThis() {
        return this;
    }

    public SettingsPage setUrlImageValue(String text) {
        pictureUrlInput.shouldBe(visible, enabled).setValue(text);
        return returnThis();
    }

    public SettingsPage setUsernameValue(String text) {
        usernameInput.shouldBe(visible, enabled).setValue(text);
        return returnThis();
    }

    public SettingsPage setBioValue(String text) {
        bioTextarea.shouldBe(visible, enabled).clear();
        bioTextarea.sendKeys(text);
        return returnThis();
    }

    public SettingsPage setEmailValue(String text) {
        emailInput.shouldBe(visible, enabled).setValue(text);
        return returnThis();
    }

    public SettingsPage setNewPasswordValue(String text) {
        passwordInput.shouldBe(visible, enabled).setValue(text);
        return returnThis();
    }

    public ProfilePage updateSettings() {
        updateSettingsButton.shouldBe(visible, enabled).click();
        return new ProfilePage();
    }
}
