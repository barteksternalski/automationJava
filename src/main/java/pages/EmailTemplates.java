package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailTemplates extends BasePage {

    public EmailTemplates(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement sloganTextInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("sloganText")));
    }

    private WebElement welcomeMessageInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("welcomeMessage")));
    }

    private WebElement buttonTextInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("buttonText")));
    }


    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //


    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

}
