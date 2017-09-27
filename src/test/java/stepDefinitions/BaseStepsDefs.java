package stepDefinitions;

import configuration.EnvironmentSetup;
import pages.*;

class BaseStepsDefs extends EnvironmentSetup {

    static LandingPage landingPage;
    static CreateSingleESlip createSingleESlip;
    static CreateUser createUser;
    static ListOfUsers listOfUsers;
    static LoginPage loginPage;
    static ListOfDrafts listOfDrafts;
    static SentESlips sentESlips;
    static EmailTemplates emailTemplates;
    static ESlipBackTemplates eSlipBackTemplates;

    static void initPages() {
        landingPage         = new LandingPage(driver, 10);
        createSingleESlip   = new CreateSingleESlip(driver, 10);
        createUser          = new CreateUser(driver, 10);
        listOfUsers         = new ListOfUsers(driver, 10);
        loginPage           = new LoginPage(driver, 15);
        listOfDrafts        = new ListOfDrafts(driver, 10);
        sentESlips          = new SentESlips(driver, 10);
        emailTemplates      = new EmailTemplates(driver, 10);
        eSlipBackTemplates  = new ESlipBackTemplates(driver, 10);
    }
}
