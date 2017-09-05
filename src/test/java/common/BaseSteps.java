package common;

import configuration.EnvironmentSetup;
import pages.LandingPage;

public class BaseSteps extends EnvironmentSetup {
    protected static LandingPage landingPage;

    protected static void initPages() {
        landingPage = new LandingPage(driver, 10);
    }
}
