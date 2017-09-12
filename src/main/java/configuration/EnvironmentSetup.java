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
//        Map<String, Object> caps = new HashMap<>();
//        caps.put(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        caps.put("requireWindowFocus", true);
//        driverFactory.updateCapabilities(caps);
        driver = driverFactory.getDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary(System.getProperty("user.dir").concat("\\src\\main\\resources\\drivers\\chromedriver.exe"));
//        options.addArguments("start-maximized");
//        driver = new ChromeDriver(options);
    }
}
