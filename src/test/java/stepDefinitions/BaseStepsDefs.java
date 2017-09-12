package stepDefinitions;

import configuration.EnvironmentSetup;
import pages.CreateSingleESlip;
import pages.LandingPage;

public class BaseStepsDefs extends EnvironmentSetup {

    protected static LandingPage landingPage;
    protected static CreateSingleESlip createSingleESlip;

    protected static void initPages() {
        landingPage         = new LandingPage(driver, 10);
        createSingleESlip   = new CreateSingleESlip(driver, 10);
    }
}
