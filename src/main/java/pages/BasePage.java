package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import web.BaseWebMobileElement;

import java.util.List;

public class BasePage extends BaseWebMobileElement {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public BasePage(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    public WebElement getElement(By by) {
        return getWebElement(by);
    }

    public WebElement getElement(ExpectedCondition condition) {
        return getWebElement(condition);
    }

    public List<WebElement> getElements(By by) {
        return getWebElements(by);
    }

    public List<WebElement> getElements(ExpectedCondition condition) {
        return getWebElements(condition);
    }

}
