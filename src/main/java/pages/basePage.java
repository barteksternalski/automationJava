package pages;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class basePage {
    protected int timeOfWaiting = 600;
    protected FluentWait<WebDriver> wait;
    protected WebDriver driver;

    public basePage(WebDriver driver) {
        this.driver = driver;
        this.wait = (new FluentWait(driver)).withTimeout((long)this.timeOfWaiting, TimeUnit.SECONDS).pollingEvery(2L, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);

    }

    public basePage(WebDriver driver, int timeOfWaiting) {
        this.driver = driver;
        this.wait = (new FluentWait(driver)).withTimeout((long)timeOfWaiting, TimeUnit.SECONDS).pollingEvery(10L, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);
        this.timeOfWaiting = timeOfWaiting;
    }

    public void waitForLoad(int timeout) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(pageLoadCondition);
    }


    protected WebElement getElement(final By by) {
        return (WebElement)this.wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }

    protected WebElement getElement(ExpectedCondition<WebElement> expectedCondition) {
        return (WebElement)this.wait.until(expectedCondition);
    }

    protected List<WebElement> getElements(final By by) {
        return (List)this.wait.until(new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver d) {
                return d.findElements(by);
            }
        });
    }

    protected List<WebElement> getElements(ExpectedCondition<List<WebElement>> expectedCondition) {
        return (List)this.wait.until(expectedCondition);
    }

    public String getPageTitle() {
        waitForLoad(15);
        return driver.getTitle();
    }



}
