package com.bb.recruitment.pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;
import static com.bb.recruitment.utils.WaitUtils.waitForConditionToBeFulfilled;

import com.bb.recruitment.utils.WaitUtils;
import com.codeborne.selenide.SelenideElement;

public class SignInPage extends PageObject<SignInPage> {

    private final SelenideElement emailInput = $(xpath("//*[@formcontrolname='email']"));
    private final SelenideElement passwordInput = $(xpath("//*[@formcontrolname='password']"));
    private final SelenideElement signInButton = $(xpath("//button[normalize-space(text())='Sign in']"));

    @Override
    protected void waitToBeLoaded() {
        WaitUtils.waitForConditionToBeFulfilled(() -> getCurrentURL().contains("login"), 5000L);
        $(xpath("//h1[normalize-space(text())='Sign in']")).shouldBe(visible);
    }

    @Override
    public SignInPage returnThis() {
        return this;
    }

    public SignInPage setEmailValue(String text) {
        emailInput.shouldBe(visible, enabled).sendKeys(text);
        return returnThis();
    }

    public SignInPage setPasswordValue(String text) {
        passwordInput.shouldBe(visible, enabled).sendKeys(text);
        return returnThis();
    }

    public HomePage signIn() {
        signInButton.shouldBe(visible, enabled).click();
        return new HomePage();
    }
}
