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

    WebElement dashboardNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'dashboard')]")));
    }

    WebElement eslipDraftsNavigationLink() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@routerlink,'drafts')]")));
    }

    WebElement pageTitle() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//h1")));
    }


    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void navigateToDrafts() {
        this.eslipDraftsNavigationLink().click();
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
