package uiscenarios;

import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Configuration;

public class BaseUiTest {

    private static final ResourceBundle rb = ResourceBundle.getBundle("config");

    @BeforeEach
    public void setUpEnvironment() {
        Configuration.remote = rb.getString("selenide.remoteURL");
        Configuration.browser = rb.getString("selenide.browser");
        Configuration.headless = rb.getObject("selenide.headless").equals("true");
        Configuration.startMaximized = rb.getObject("selenide.isMaximized").equals("true");
        Configuration.reportsFolder = rb.getString("selenide.screenshotsFolder");
    }

}
