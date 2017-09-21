package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListOfDrafts extends BasePage {

    public ListOfDrafts(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    };

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> eSlipByNameCheckbox(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//input")));
    }

    private List<WebElement> eSlipByNameStatus(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//td[9]")));
    }

    private List<WebElement> eSlipByName(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]")));
    }

    private WebElement editButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
    }

    private WebElement deleteButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']")));
    }

    private WebElement sendButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Send']")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void selectESlipByName(String name) {
        this.eSlipByNameCheckbox(name).get(0).click();
    }

    public void editESlip() {
        this.editButton().click();
    }

    public void deleteESlip() {
        this.deleteButton().click();
    }

    public void sendESlip() {
        this.sendButton().click();
    }

    public String getESlipState(String name) {
        return this.eSlipByNameStatus(name).get(0).getText();
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean verifyIfESlipInDisplayedOnList(String name) {
        return this.eSlipByName(name).size() > 0;
    }

}
