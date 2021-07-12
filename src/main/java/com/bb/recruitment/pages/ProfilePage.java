package com.bb.recruitment.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;
import static com.bb.recruitment.utils.WaitUtils.waitForConditionToBeFulfilled;

import java.util.List;

import com.bb.recruitment.utils.WaitUtils;
import com.codeborne.selenide.ElementsCollection;

public class ProfilePage extends PageObject<ProfilePage> {

    private final ElementsCollection profileData = $$(xpath("//*[@class='user-info']//*"));

    @Override
    protected void waitToBeLoaded() {
        WaitUtils.waitForConditionToBeFulfilled(() -> getCurrentURL().contains("profile"), 5000L);
        $(xpath("//*[normalize-space(text())='Edit Profile Settings']")).shouldBe(visible);
    }

    @Override
    public ProfilePage returnThis() {
        return this;
    }

    public List<String> getProfileData() {
        return profileData.texts();
    }
}
