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

    private List<WebElement> userByNameCheckbox(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::a[contains(text(),'" + name + "')]]//input")));
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
        this.userByNameCheckbox(name).get(0).click();
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

    public void restPassword() {
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

    public boolean verifyUserActivationStatus(String user) {
        return this.userByNameActivationStatus(user).get(0).getText().equals("true");
    }

}
