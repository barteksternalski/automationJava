package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SentESlips extends BasePage{

    public SentESlips(WebDriver driver, int timeOut) {
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

    private List<WebElement> eSlipByNameState(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//td[9]")));
    }

    private List<WebElement> eSlipByName(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]")));
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

    public void selectESlipByName(String name) {
        this.eSlipByNameCheckbox(name).get(0).click();
    }

    public String getESlipState(String name) {
        return this.eSlipByNameState(name).get(0).getText();
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

    public boolean verifyIfESlipInDisplayedOnList(String name) {
        try {Thread.sleep(1000); } catch (Exception e) {};
        return this.eSlipByName(name).size() > 0;
    }


}
