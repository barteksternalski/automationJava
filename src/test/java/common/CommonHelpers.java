package common;

import configuration.EnvironmentSetup;
import pages.LandingPage;

public class CommonHelpers extends EnvironmentSetup {
    protected static LandingPage landingPage;

    protected static void initPages() {
        landingPage = new LandingPage(driver);
    }
}
