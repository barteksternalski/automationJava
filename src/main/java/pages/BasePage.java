package pages;

import net.itarray.automotion.tools.web.BaseWebMobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void selectElementFromDropdown(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }


}
