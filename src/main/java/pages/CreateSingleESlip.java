package pages;

import cucumber.api.DataTable;
import helpers.Procedures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CreateSingleESlip extends BasePage {

    public CreateSingleESlip(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    };

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private WebElement nameInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("customerName")));
    }

    private WebElement phoneNumberInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("phoneNumber")));
    }

    private WebElement emailInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("email")));
    }

    private WebElement provinceDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("provinceId")));
    }

    private WebElement languageDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("preferedLang")));
    }

    private WebElement policyNumberInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("policyNumber")));
    }

    private WebElement insurerDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("insurer")));
    }

    private WebElement brokerageInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("brokerageName")));
    }

    private WebElement effectiveDateInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("effectiveDate")));
    }

    private WebElement expirationDateInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("expirationDate")));
    }

    private WebElement insurerAddressInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("insurerAddress")));
    }

    private WebElement contactAddress1InputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("address1")));
    }

    private WebElement contactAddress2InputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("address2")));
    }

    private WebElement contactCityInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("city")));
    }

    private WebElement contactPostalCodeInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("postalCode")));
    }


    private WebElement saveDraftButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    }

    private WebElement nextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='NEXT']")));
    }

    private WebElement backButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='BACK']")));
    }

    private WebElement vehicleYearInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("vehicleYear")));
    }

    private WebElement vehicleMakeInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("vehicleMake")));
    }

    private WebElement vehicleModelInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("vehicleModel")));
    }

    private WebElement vinNumberInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("vinNumber")));
    }

    private WebElement addVehicleButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Vehicle']")));
    }

    private WebElement backTextTitleInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryTitle")));
    }

    private WebElement backTextEntryInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryText")));
    }

    private WebElement clearBackTextEntryButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Clear']")));
    }

    private WebElement addBackTextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add entry']")));
    }

    private List<WebElement> editVehicleButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
    }

    private List<WebElement> removeVehicleButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Trash']")));
    }

    private List<WebElement> moveUpVehicleButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Up']")));
    }

    private List<WebElement> moveDownVehicleButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Down']")));
    }

    private List<WebElement> editBackTextButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
    }

    private List<WebElement> removeBackTextButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Trash']")));
    }

    private List<WebElement> moveUpBackTextButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Up']")));
    }

    private List<WebElement> moveDownBackTextButtonList() {
        return getElements(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Down']")));
    }


    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void fillCustomerInformation(DataTable table) {

        List<List<String>> temp = table.raw();

        Procedures.fillInputFieldBasedOnDataTable(temp, "Name", this.nameInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Number", this.policyNumberInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Email", this.emailInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Phone Number", this.phoneNumberInputField());
        Procedures.selectDropdownValueBasedOnDataTable(temp, "Preferred Language", this.languageDropdown());
        Procedures.selectDropdownValueBasedOnDataTable(temp, "Province", this.provinceDropdown());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Address 1", this.contactAddress1InputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Address 2", this.contactAddress2InputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "City", this.contactCityInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Postal Code", this.contactPostalCodeInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Effective Date", this.effectiveDateInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Expiration Date", this.expirationDateInputField());
        Procedures.selectDropdownValueBasedOnDataTable(temp, "Insurer", this.insurerDropdown());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Brokerage", this.brokerageInputField());

    }

    public void fillVehicleInformation(DataTable table) {

        List<List<String>> temp = table.raw();

        if (!temp.get(0).get(1).equals("{null}"))   this.vehicleYearInputField().sendKeys(temp.get(0).get(1));
        if (!temp.get(1).get(1).equals("{null}"))   this.vehicleMakeInputField().sendKeys(temp.get(1).get(1));
        if (!temp.get(2).get(1).equals("{null}"))   this.vehicleModelInputField().sendKeys(temp.get(2).get(1));
        if (!temp.get(3).get(1).equals("{null}"))   this.vinNumberInputField().sendKeys(temp.get(3).get(1));
    }

    public void editVehicleInformation(int number, DataTable table) {
        this.editVehicleButtonList().get(number - 1).click();
        fillVehicleInformation(table);
    }

    public void removeVehicle(int number) {
        this.removeVehicleButtonList().get(number - 1).click();
    }

    public void moveVehicleUp(int number) {
        this.moveUpVehicleButtonList().get(number - 1).click();
    }

    public void moveVehicleDown(int number) {
        this.moveDownVehicleButtonList().get(number - 1).click();
    }

    public void fillBackText(DataTable table) {

        List<List<String>> temp = table.raw();

        if (!temp.get(0).get(1).equals("{null}"))   this.backTextTitleInputField().sendKeys(temp.get(0).get(1));
        if (!temp.get(1).get(1).equals("{null}"))   this.backTextEntryInputField().sendKeys(temp.get(1).get(1));

    }

    public void editBackTextInformation(int number, DataTable table) {
        this.editBackTextButtonList().get(number - 1).click();
        fillBackText(table);
    }

    public void removeBackText(int number) {
        this.removeBackTextButtonList().get(number - 1).click();
    }

    public void moveBackTextUp(int number) {
        this.moveUpBackTextButtonList().get(number - 1).click();
    }

    public void moveBackTextDown(int number) {
        this.moveDownBackTextButtonList().get(number - 1).click();
    }

    public void saveDraft() {
        this.saveDraftButton().click();
    }

    public void next() {
        this.nextButton().click();
    }

    public void back() {
        this.nextButton().click();
    }


    public void addVehicle() {
        this.addVehicleButton().click();
    }

    public void clearBackTextEntry() {
        this.clearBackTextEntryButton().click();
    }

    public void addBackTextEntry() {
        this.addBackTextButton().click();
    }


    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //


}
