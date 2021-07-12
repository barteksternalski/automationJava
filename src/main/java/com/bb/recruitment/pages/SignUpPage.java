package com.bb.recruitment.pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;
import static com.bb.recruitment.utils.WaitUtils.waitForConditionToBeFulfilled;

import com.bb.recruitment.utils.WaitUtils;
import com.codeborne.selenide.SelenideElement;

public class SignUpPage extends PageObject<SignUpPage> {

    private final SelenideElement usernameInput = $(xpath("//*[@formcontrolname='username']"));
    private final SelenideElement emailInput = $(xpath("//*[@formcontrolname='email']"));
    private final SelenideElement passwordInput = $(xpath("//*[@formcontrolname='password']"));
    private final SelenideElement signUpButton = $(xpath("//button[normalize-space(text())='Sign up']"));

    @Override
    protected void waitToBeLoaded() {
        WaitUtils.waitForConditionToBeFulfilled(() -> getCurrentURL().contains("register"), 5000L);
        $(xpath("//h1[normalize-space(text())='Sign up']")).shouldBe(visible);
    }

    @Override
    public SignUpPage returnThis() {
        return this;
    }

    public SignUpPage setUsernameValue(String text) {
        usernameInput.shouldBe(visible, enabled).sendKeys(text);
        return returnThis();
    }

    public SignUpPage setEmailValue(String text) {
        emailInput.shouldBe(visible, enabled).sendKeys(text);
        return returnThis();
    }

    public SignUpPage setPasswordValue(String text) {
        passwordInput.shouldBe(visible, enabled).sendKeys(text);
        return returnThis();
    }

    public HomePage signUp() {
        signUpButton.shouldBe(visible, enabled).click();
        return new HomePage();
    }

}
