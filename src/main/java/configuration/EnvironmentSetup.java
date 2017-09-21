package configuration;

        import net.itarray.automotion.tools.driver.WebDriverFactory;
        import net.itarray.automotion.tools.property.PropertyLoader;
        import org.openqa.selenium.WebDriver;

public class EnvironmentSetup {

    public static PropertyLoader propertyLoader = new PropertyLoader("App.properties");
    public static String prefix;
    public static WebDriver driver;

    public static void initDriver() {
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver();
    }
}
