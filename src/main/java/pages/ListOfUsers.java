package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListOfUsers extends BasePage {

    public ListOfUsers(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> listOfUserNames() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@href,'edituser')]")));
    }

    private List<WebElement> userByNameCheckbox(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::a[contains(text(),'" + name + "')]]//div[contains(@class,'container')]")));
    }

    private List<WebElement> userByNameActivationStatus(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::a[contains(text(),'" + name + "')]]//td[2]")));
    }

    // ************ buttons ************* //

    private WebElement editButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
    }

    private WebElement deleteButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='Delete']")));
    }

    private WebElement activateButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Activate']")));
    }

    private WebElement deactivateButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Deactivate']")));
    }

    private WebElement resetPasswordButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Rest password']")));
    }

    // ********* navigation *********** //

    private WebElement paginationNextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='pagination-next']")));
    }

    private WebElement paginationPreviousButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='pagination-previous']")));
    }

    private WebElement paginationDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//div[descendant::pagination-controls]//select")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void selectUserByName(String name) {
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.userByNameCheckbox(name).size() > 0) {
                    this.userByNameCheckbox(name).get(0).click();
                    break;
                }
            } catch(Exception e) {
                System.out.println("INFO: user not displayed on current page");
            }
            try {
                if (this.paginationNextButton().isEnabled()) {
                    System.out.println("INFO: Navigate to next page");
                    paginationNext();
                }
            } catch(Exception e) {
                System.out.println("INFO: Last page");
                contFlag = false;
            }
        }
    }

    public void editUser() {
        this.editButton().click();
    }

    public void deleteUser() {
        this.deleteButton().click();
    }

    public void activateUser() {
        this.activateButton().click();
    }

    public void deactivateUser() {
        this.deactivateButton().click();
    }

    public void resetPassword() {
        this.resetPasswordButton().click();
    }

    public void paginationNext() {
        this.paginationNextButton().click();
    }

    public void paginationPrevious() {
        this.paginationPreviousButton().click();
    }

    public void paginationSelect(String number) {
        selectElementFromDropdown(this.paginationDropdown(), number);
    }

    public String getUserActivationStatus(String user) {
        boolean contFlag = true;
        String result = "";
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.userByNameActivationStatus(user).size() > 0) {
                    result = this.userByNameActivationStatus(user).get(0).getText();
                    break;
                }
            } catch(Exception e) {
                System.out.println("INFO: user not displayed on current page");
            }
            try {
                if (this.paginationNextButton().isEnabled()) {
                    System.out.println("INFO: Navigate to next page");
                    paginationNext();
                }
            } catch(Exception e) {
                System.out.println("INFO: Last page");
                contFlag = false;
            }
        }
        return result;
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean userVisibleOnUserList(String user) {
        boolean contFlag = true;
        while(contFlag) {
            try {Thread.sleep(1000);} catch(Exception e) {System.out.println("Sleep...");}
            if (this.listOfUserNames().size() > 0) {
                for (WebElement temp : this.listOfUserNames()) {
                    if (temp.getText().equals(user)) {
                        System.out.println("INFO: User found!");
                        return true;
                    }
                }
                System.out.println("INFO: User not found!");
            } else {
                System.out.println("INFO: User list is empty!");
            }
            try {
                if (this.paginationNextButton().isEnabled()) {
                    System.out.println("INFO: Navigate to next page");
                    paginationNext();
                }
            } catch(Exception e) {
                System.out.println("INFO: Last page");
                contFlag = false;
            }
        }
        return false;
    }

}
