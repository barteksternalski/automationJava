package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    };

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement userNameInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("cred_userid_inputtext")));
    }

    private WebElement passwordInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("passwordInput")));
    }

    private WebElement avanadeTileLogin() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Avanade']")));
    }

    private WebElement loginButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void login(String username, String password) {
        this.userNameInputField().sendKeys(username, Keys.TAB);
        this.avanadeTileLogin().click();
        this.passwordInputField().sendKeys(password);
        this.loginButton().click();
    }


    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

}
