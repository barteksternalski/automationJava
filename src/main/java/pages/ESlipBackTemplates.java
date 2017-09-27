package pages;

import cucumber.api.DataTable;
import helpers.Procedures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ESlipBackTemplates extends BasePage{

    public ESlipBackTemplates(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement backTextTitleInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryTitle")));
    }

    private WebElement backTextEntryInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryText")));
    }

    private WebElement backTextReadOnlyCheckbox() {
        return getElement(ExpectedConditions.elementToBeClickable(By.name("entryReadonly")));
    }

    private WebElement editBackTextTitleInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='entryTitle']")));
    }

    private WebElement editBackTextEntryInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='entryText']")));
    }

    private WebElement editBackTextReadOnlyCheckbox() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='entryReadonly']")));
    }

    private WebElement clearBackTextEntryButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Clear']")));
    }

    private WebElement addBackTextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add entry']")));
    }

    private WebElement saveTemplateButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
    }

    private WebElement saveSectionButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/button[text()='Save']")));
    }

    private List<WebElement> editBackTextByTitleButton(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]//button[text()='Edit']")));
    }

    private List<WebElement> removeBackTextByTitleButton(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]//button[text()='Trash']")));
    }

    private List<WebElement> moveUpBackTextByTitleButton(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]//button[text()='Up']")));
    }

    private List<WebElement> moveDownBackTextByTitleButton(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]//button[text()='Down']")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //


    public void addBackTextTemplateSection(DataTable table) {

        List<List<String>> temp = table.raw();

        Procedures.fillInputFieldBasedOnDataTable(temp, "Title", this.backTextTitleInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Text", this.backTextEntryInputField());
        if (temp.get(2).get(1).equals("Yes")) this.backTextReadOnlyCheckbox().click();

        this.addBackTextButton().click();
    }

    public void editBackTextTemplateSection(String title, DataTable table) {

        List<List<String>> temp = table.raw();

        this.editBackTextByTitleButton(title).get(0).click();
        Procedures.fillInputFieldBasedOnDataTable(temp, "Title", this.editBackTextTitleInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Text", this.editBackTextEntryInputField());
        if (temp.get(2).get(1).equals("Yes"))
            if (this.editBackTextReadOnlyCheckbox().getAttribute("ng-reflect model").equals("false"))
                this.backTextReadOnlyCheckbox().click();
        this.saveSectionButton().click();

    }

    public void removeBackText(String title) {
        this.removeBackTextByTitleButton(title).get(0).click();
    }

    public void moveBackTextUp(String title) {
        this.moveUpBackTextByTitleButton(title).get(0).click();
    }

    public void moveBackTextDown(String title) {
        this.moveDownBackTextByTitleButton(title).get(0).click();
    }

    public void saveTemplate() {
        this.saveTemplateButton().click();
    }


    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

}
