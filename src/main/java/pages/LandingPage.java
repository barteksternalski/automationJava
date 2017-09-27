package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement dashboardNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'dashboard')]")));
    }

    private WebElement reportingNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'reporting')]")));
    }

    private WebElement createSingleNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'createsingle')]")));
    }

    private WebElement createBulkNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'createbulk')]")));
    }

    private WebElement eslipDraftsNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'drafts')]")));
    }

    private WebElement sentESlipsNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'sent')]")));
    }

    private WebElement emailTemplatesNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'emailtemplate')]")));
    }

    private WebElement eSlipBackTemplatesNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'eslipbacktemplate')]")));
    }

    private WebElement createUserNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'createuser')]")));
    }

    private WebElement userListNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'listusers')]")));
    }

    private WebElement pageTitle() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//main/h1")));
    }

    private WebElement logoutButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Logout']")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void navigateTo(String location) {
        switch (location) {
            case "Dashboard":
                this.dashboardNavigationLink().click();
                break;
            case "Reporting":
                this.reportingNavigationLink().click();
                break;
            case "Create Single":
                this.createSingleNavigationLink().click();
                break;
            case "Create Bulk":
                this.createBulkNavigationLink().click();
                break;
            case "Drafts":
                this.eslipDraftsNavigationLink().click();
                break;
            case "Sent":
                this.sentESlipsNavigationLink().click();
                break;
            case "E-mail":
                this.emailTemplatesNavigationLink().click();
                break;
            case "E-Slip Back":
                this.eSlipBackTemplatesNavigationLink().click();
                break;
            case "Create User":
                this.createUserNavigationLink().click();
                break;
            case "User List":
                this.userListNavigationLink().click();
                break;
            default:
                System.out.println("INFO: There is no '" + location + "' navigation link!");
                break;
        }
    }

    public void logOut() {
        this.logoutButton().click();
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean verifyPageTitle(String title) {
        return this.pageTitle().getText().equals(title);
    }

    public boolean verifyMainPageIsDisplayed() {
        return logoutButton().isDisplayed();
    }

    private boolean verifyIfUserHasAccessToGivenLocation(String location) {
        try {
            switch (location) {
                case "Dashboard":
                    return this.dashboardNavigationLink().isDisplayed();
                case "Reporting":
                    return this.reportingNavigationLink().isDisplayed();
                case "Create Single":
                    return this.createSingleNavigationLink().isDisplayed();
                case "Create Bulk":
                    return this.createBulkNavigationLink().isDisplayed();
                case "Drafts":
                    return this.eslipDraftsNavigationLink().isDisplayed();
                case "Sent":
                    return this.sentESlipsNavigationLink().isDisplayed();
                case "E-mail":
                    return this.emailTemplatesNavigationLink().isDisplayed();
                case "E-Slip Back":
                    return this.eSlipBackTemplatesNavigationLink().isDisplayed();
                case "Create User":
                    return this.createUserNavigationLink().isDisplayed();
                case "User List":
                    return this.userListNavigationLink().isDisplayed();
                default:
                    System.out.println("INFO: There is no '" + location + "' navigation link!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("INFO: Location '" + location + "' is not available for given user!");
        }
        return false;
    }

    public boolean verifyAvailableModules(String accessModules) {
        boolean localFlag = true;
        List<String> modules = Arrays.asList(accessModules.split(","));
        for (String temp : modules) {
            if (verifyIfUserHasAccessToGivenLocation(temp)) {
                System.out.println("INFO: Access to '" + temp + "' module verified");
            } else {
                System.out.println("INFO: Inappropriate access to '" + temp + "' module.");
                localFlag = false;
            }
        }
        return localFlag;
    }

    public boolean verifyUnavailableModules(String accessModules) {
        boolean localFlag = true;
        List<String> modules = Arrays.asList(accessModules.split(","));
        for (String temp : modules) {
            if (verifyIfUserHasAccessToGivenLocation(temp)) {
                System.out.println("INFO: Inappropriate access to '" + temp + "' module.");
                localFlag = false;
            } else {
                System.out.println("INFO: Access to '" + temp + "' module verified");
            }
        }
        return localFlag;
    }


}
