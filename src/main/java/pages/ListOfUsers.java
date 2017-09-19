package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListOfUsers extends BasePage {

    public ListOfUsers(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    };

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> listOfUserNames() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@href,'edituser')]")));
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

    public boolean userVisibleOnUserList(String user) {
        if (this.listOfUserNames().size() > 0) {
            for (WebElement temp : this.listOfUserNames()) {
                if (temp.getText().equals(user))
                    return true;
            }
            System.out.println("INFO: User not found!");
        } else {
            System.out.println("INFO: User list is empty!");
        }
        return false;
    }

}
