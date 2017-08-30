package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static environment.EnvironmentFactory.*;

public class WebDriverFactory {

    private WebDriver webDriver;

    private static void setChromeDriver() {
        Platform platform = Platform.getCurrent();
        String chromeDriverLocation = "src/main/resources/drivers/chromedriver" + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
    }

    private static void setGeckoDriver() {
        Platform platform = Platform.getCurrent();
        String geckoDriverLocation = "src/main/resources/drivers/geckodriver" + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
        System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
    }

    public WebDriver getDriver() {
        if(isChrome()) {
            setChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            webDriver = new ChromeDriver(options);
        } else if(isFirefox()) {
            setGeckoDriver();
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }
}
