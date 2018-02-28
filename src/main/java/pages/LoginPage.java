package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

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

    public void navigation() {
        getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button"))).click();

        List<WebElement> listToSize = getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='box-apps-menu']/li/a")));
        for(int i = 0; i < listToSize.size(); i++) {
            List<WebElement> menuTop = getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='box-apps-menu']/li/a")));
            menuTop.get(i).click();

            List<WebElement> nestedMenuToSize = new ArrayList();
            try {
                nestedMenuToSize = getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul//span[@class='name']")));
            } catch (Exception e) {
                System.out.println("There is no nested menu");
            }

            if (nestedMenuToSize.size() > 0) {
                for (int j = 0; j < nestedMenuToSize.size(); j++) {
                    List<WebElement> nestedMenu = getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul//span[@class='name']")));
                    nestedMenu.get(j).click();
                    Assert.assertEquals(getElement(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText(), getElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul/li[@class='selected']//span[@class='name']")).getText());
                }
            } else {
                Assert.assertEquals(getElement(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText(), getElement(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/a/span[@class='name']")).getText());
            }

        }
    }


}
