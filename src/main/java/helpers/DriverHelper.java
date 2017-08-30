package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static environment.EnvironmentFactory.isFirefox;
import static java.lang.Thread.sleep;

public class DriverHelper {

    public static void scrollDownWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,1000)", "");
    }

    public static void scrollUpWeb(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,-1000)", "");
    }

    public static void zoomInOutPage(WebDriver driver, int zoomPercent) {
        if (zoomPercent > 0) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            if (isFirefox()) {
                jse.executeScript("document.body.style.MozTransform = 'scale(" + (zoomPercent / 100f) + ")';");
            } else {
                jse.executeScript("document.body.style.zoom = '" + zoomPercent + "%'");
            }
        }
    }

    public static void wait(int seconds) throws InterruptedException {
        sleep(1000 * seconds);
    }

    public static String takeScreenshot(WebDriver driver) throws Exception {
        String fullFileName = System.getProperty("user.dir")
                + "/target/reports/screenshots/screenshot_"
                + System.currentTimeMillis() + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(fullFileName));

        return fullFileName;
    }

    public static void sendKeys(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public static void click(WebDriver driver, WebElement element) {
        waitForPageIsReady(driver);

        int count = 0;
        while (count < 10) {
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            } catch (WebDriverException ex) {
                count++;
            }
        }
    }

    public static boolean waitForPageIsReady(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        final JavascriptExecutor executor = (JavascriptExecutor) driver;

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) executor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return executor.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

}
