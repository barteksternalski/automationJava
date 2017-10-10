package configuration;

import net.itarray.automotion.tools.driver.WebDriverFactory;
import net.itarray.automotion.tools.property.PropertyLoader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class EnvironmentSetup {

    public static PropertyLoader propertyLoader = new PropertyLoader("App.properties");
    public static String prefix;
    public static WebDriver driver;

    public static void initDriver(String browser) {
//        WebDriverFactory driverFactory = new WebDriverFactory();
//        driver = driverFactory.getDriver();
        Platform platform;

        switch (browser) {
            case "Chrome":
                platform = Platform.getCurrent();
                String chromeBinary = "src/main/resources/drivers/chromedriver" + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
                System.setProperty("webdriver.chrome.driver", chromeBinary);
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless");
                //chromeOptions.addArguments("--window-size=1680,1050");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "Firefox":
                platform = Platform.getCurrent();
                String geckoBinary = "src/main/resources/drivers/geckodriver" + (platform.toString().toUpperCase().contains("WIN") ? ".exe" : "");
                System.setProperty("webdriver.gecko.driver", geckoBinary);
                driver = new FirefoxDriver();
                break;
            case "IE":
                String ieBinary = "src/main/resources/drivers/IEDriverServer.exe";
                System.setProperty("webdriver.ie.driver", ieBinary);
                driver = new InternetExplorerDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "Edge":
                String edgeBinary = "src/main/resources/drivers/MicrosoftWebDriver.exe";
                System.setProperty("webdriver.edge.driver", edgeBinary);
                driver = new EdgeDriver();
                break;
        }


    }
}
