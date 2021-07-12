package com.bb.recruitment.pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

import com.codeborne.selenide.SelenideElement;

public class HomePage extends PageObject<HomePage> {

    private final static String NAVIGATION_BUTTONS = "//*[@class='nav-item' and descendant::*[contains(normalize-space(text()),'%s')]]";

    private final SelenideElement activeFeedTab = $(
            xpath("//*[@class='feed-toggle']//*[@class='nav-item' and not(@hidden)]//*[contains(@class,'active')]"));
    private final SelenideElement userNavLink = $(xpath("//*[contains(@href,'profile')]"));

    @Override
    protected void waitToBeLoaded() {
        $(className("home-page")).shouldBe(visible);
    }

    @Override
    public HomePage returnThis() {
        return this;
    }

    public SignInPage openSignInPage() {
        $(xpath(format(NAVIGATION_BUTTONS, "Sign in"))).shouldBe(visible, enabled).click();
        return new SignInPage();
    }

    public SignUpPage openSignUpPage() {
        $(xpath(format(NAVIGATION_BUTTONS, "Sign up"))).shouldBe(visible, enabled).click();
        return new SignUpPage();
    }

    public boolean isSettingsButtonVisible() {
        return $(xpath(format(NAVIGATION_BUTTONS, "Settings"))).isDisplayed();
    }

    public SettingsPage openSettingsPage() {
        $(xpath(format(NAVIGATION_BUTTONS, "Settings"))).shouldBe(visible, enabled).click();
        return new SettingsPage();
    }

    public String getActiveFeedTab() {
        return activeFeedTab.shouldBe(visible).getText().trim();
    }

    public String getLoggedUserLinkLabel() {
        return userNavLink.shouldBe(visible).getText().trim();
    }
}
