package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.MessageFormat;

public class homePage extends basePage{

    public homePage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // *************************** //
    //           elements          //
    // *************************** //

    private WebElement cookies() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'allow-all')]")));
    }

    private WebElement topMenuSearch() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='search']")));
    }

    private WebElement topMenuContactUs() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']")));
    }

    private WebElement topMenuSearchInput() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='searchModal']//form[@name='loginForm']/input")));
    }

    private WebElement mainMenuNavigation(String tab) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[@id=''main-navbar'']//a[contains(text(),''{0}'')]", tab))));
    }

    // *************************** //
    //            methods          //
    // *************************** //
    public void acceptCookies() {
        cookies().click();
    }

    public void searchForGivenTopic(String topic) {
        topMenuSearch().click();
        topMenuSearchInput().sendKeys(topic, Keys.ENTER);
    }

    public void goToContactUs() {
        topMenuContactUs().click();
    }

    public void gotoMainTab(String tab) {
        Actions actionBuilder = new Actions(driver);
        actionBuilder
                .moveToElement(mainMenuNavigation(tab))
                .pause(500)
                .click()
                .pause(1000)
                .build()
                .perform();
    }

}
