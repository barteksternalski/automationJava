package environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class driverFactory {


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public synchronized static void setRemoteDriver(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String nodeURL = "http://localhost:4444/wd/hub";
        if (browser.equals("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            capabilities = DesiredCapabilities.chrome();
        }

        DesiredCapabilities finalCapabilities = capabilities;
        driver = ThreadLocal.withInitial(() -> {
            try {
                return new RemoteWebDriver(new URL(nodeURL), finalCapabilities);
            } catch(MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        });


    }

    public synchronized static void setDriver (String browser) {
        if (browser.equals("firefox")) {
            driver = ThreadLocal.withInitial(() -> new FirefoxDriver(getFirefoxOptions()));
        } else if (browser.equals("chrome")) {
            driver = ThreadLocal.withInitial(() -> new ChromeDriver(getChromeOptions()));
        }
    }

    public synchronized static WebDriver getDriver () {
        return driver.get();
    }

    //Get Chrome Options
    private static ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION\\browserDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        return options;
        /*ChromeDriverService service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        ChromeDriver driver = new ChromeDriver(service, options);*/
    }

    //Get Firefox Options
    private static FirefoxOptions getFirefoxOptions () {
        System.setProperty("webdriver.gecko.driver", "C:\\AUTOMATION\\browserDrivers\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }

}
