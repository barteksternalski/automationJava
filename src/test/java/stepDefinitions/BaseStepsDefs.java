package stepDefinitions;

import configuration.EnvironmentSetup;
import pages.*;

public class BaseStepsDefs extends EnvironmentSetup {

    protected static LandingPage landingPage;
    protected static CreateSingleESlip createSingleESlip;
    protected static CreateUser createUser;
    protected static ListOfUsers listOfUsers;
    protected static LoginPage loginPage;
    protected static ListOfDrafts listOfDrafts;
    protected static SentESlips sentESlips;

    protected static void initPages() {
        landingPage         = new LandingPage(driver, 10);
        createSingleESlip   = new CreateSingleESlip(driver, 10);
        createUser          = new CreateUser(driver, 10);
        listOfUsers         = new ListOfUsers(driver, 10);
        loginPage           = new LoginPage(driver, 15);
        listOfDrafts        = new ListOfDrafts(driver, 10);
        sentESlips          = new SentESlips(driver, 10);
    }
}
