package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GlobalConfProvinces extends BasePage {

    public GlobalConfProvinces(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> provinceByNameEdit(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//button[@title='Edit']")));
    }

    private List<WebElement> provinceByNameRemove(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//button[@title='Remove']")));
    }

    private List<WebElement> provinceByName(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]")));
    }

    private WebElement saveButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
    }

    private WebElement clearButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Clear']")));
    }

    private WebElement addEntryButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Entry']")));
    }

    private WebElement newProvinceNameInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryTitle")));
    }

    private WebElement newProvinceCodeInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryText")));
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

    public boolean verifyIfProvinceIsListed(String name) {
        return this.provinceByName(name).size() > 0;
    }


}
