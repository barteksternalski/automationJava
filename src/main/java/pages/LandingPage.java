package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    };

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement dashboardNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'dashboard')]")));
    }

    private WebElement eslipDraftsNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'drafts')]")));
    }

    private WebElement createUserNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'createuser')]")));
    }

    private WebElement userListNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'listusers')]")));
    }

    private WebElement pageTitle() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("////main//h1")));
    }


    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void navigateToDrafts() {
        this.eslipDraftsNavigationLink().click();
    }
    public void navigateToCreateUser() {
        this.createUserNavigationLink().click();
    }
    public void navigateToUserList() {
        this.userListNavigationLink().click();
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean verifyPageTitle(String title) {
        return this.pageTitle().getText().equals(title);
    }

}
