package environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class driverFactory {

    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();

    public enum DriverType {
        CHROME,
        FIREFOX,
        SAFARI,
        IE
    }

    //chrome driver supplier
    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION\\browserDrivers\\chromedriver.exe");
        return new ChromeDriver();
    };

    //firefox driver supplier
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        System.setProperty("webdriver.gecko.driver", "C:\\AUTOMATION\\browserDrivers\\geckodriver.exe");
        return new FirefoxDriver();
    };

    static{
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
    }

    //return a new driver from the map
    public static final WebDriver getDriver(DriverType type){
        return driverMap.get(type).get();
    }

}
