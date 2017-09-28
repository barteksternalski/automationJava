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

    private List<WebElement> backTextsList() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//eslip-back-text//table//tr")));
    }

    private List<WebElement> backTextPositionOnTheList(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]//td[1]")));
    }

    private List<WebElement> backTextWithTitleExistance(String title) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + title + "']]")));
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

    public void removeBackTemplateSection(String title) {
        this.removeBackTextByTitleButton(title).get(0).click();
    }

    public void moveBackTemplateSectionUp(String title) {
        this.moveUpBackTextByTitleButton(title).get(0).click();
    }

    public void moveBackTemplateSectionDown(String title) {
        this.moveDownBackTextByTitleButton(title).get(0).click();
    }

    public void saveBackTemplate() {
        this.saveTemplateButton().click();
    }

    public int getBackTemplateSectionByTitlePosition(String title) {
        System.out.println("INFO: Current back test '" + title +"' position: " + this.backTextPositionOnTheList(title).get(0).getText());
        return Integer.parseInt(this.backTextPositionOnTheList(title).get(0).getText());
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean verifyIfBackTemplateSectionWithTitleIsListed(String title) {
        try {
            return this.backTextWithTitleExistance(title).size() > 0;
        } catch (Exception e) {
            System.out.println("INFO: Back text with title'" + title + "' not visible on list");
        }
        return false;
    }

}
