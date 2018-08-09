package runner;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.*;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions( tags = {"@Smoke"},
        format = { "pretty", "html:target/cucumber" },
        glue = "src/test/java/stepDefinitions",
        features = "src/test/resources/features",
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter"} )

public class TestRunner {

    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("target/reports/myreport.html");
    }

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        //Reporter.setSystemInfo("user", System.getProperty("user.name"));
        //Reporter.setSystemInfo("os", "Windows");
        //Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
