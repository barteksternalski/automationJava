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
        return getElement(ExpectedConditions.elementToBeClickable(By.id("cred_password_inputtext")));
    }

    private WebElement avanadeTileLogin() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Avanade']")));
    }

    private WebElement useAnotherAccountButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//tr[contains(@class,'another_account')]")));
    }

    private WebElement loginButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("cred_sign_in_button")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void login(String username, String password) throws Exception {
        try {
            this.useAnotherAccountButton().click();
        } catch (Exception e) {
            System.out.println("INFO: First account to login");
        }
        this.userNameInputField().sendKeys(username, Keys.TAB);
        Thread.sleep(1000);
        this.passwordInputField().sendKeys(password);
        this.loginButton().click();
    }


    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

}
