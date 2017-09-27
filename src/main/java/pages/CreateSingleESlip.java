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
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    // ******************************** //
    // ******** customer info ********* //
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

    private List<WebElement> dropdownOptions() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//md-option")));
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

    // ******************************** //
    // ********* vehicle info ********* //
    // ******************************** //

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

    private WebElement editVehicleYearInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='year']")));
    }

    private WebElement editVehicleMakeInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='make']")));
    }

    private WebElement editVehicleModelInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='model']")));
    }

    private WebElement editVinNumberInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='vinNumber']")));
    }

    private List<WebElement> vehicleList() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//vehicle-information//table//tr")));
    }

    private List<WebElement> vehiclePositionOnTheList(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]//td[1]")));
    }

    private List<WebElement> vehicleWithVinExistance(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]")));
    }
    // ******************************** //
    // *********** back text ********** //
    // ******************************** //

    private WebElement backTextTitleInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryTitle")));
    }

    private WebElement backTextEntryInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("entryText")));
    }

    private WebElement editBackTextTitleInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='entryTitle']")));
    }

    private WebElement editBackTextEntryInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//td/*[@name='entryText']")));
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
    // ********* send preview ********* //
    // ******************************** //

    private WebElement sendPreviewSalutationInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("salutation")));
    }

    private WebElement sendPreviewMessageInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("message")));
    }

    // ******************************** //
    // *********** buttons ************ //
    // ******************************** //

    private WebElement addVehicleButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Vehicle']")));
    }

    private WebElement saveDraftButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    }

    private WebElement nextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Next']")));
    }

    private WebElement backButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Back']")));
    }

    private WebElement clearBackTextEntryButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Clear']")));
    }

    private WebElement addBackTextButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add entry']")));
    }

    private List<WebElement> editVehicleByVinButton(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]//button[text()='Edit']")));
    }

    private WebElement saveButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
    }

    private WebElement sendESlipButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='SEND E-SLIP']")));
    }

    private List<WebElement> removeVehicleByVinButton(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]//button[text()='Trash']")));
    }

    private List<WebElement> moveUpVehicleByVinButton(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]//button[text()='Up']")));
    }

    private List<WebElement> moveDownVehicleByVinButton(String vin) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[2][text()='" + vin + "']]//button[text()='Down']")));
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

    private WebElement pageTitle() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//main//h1")));
    }

    // ********** warnings ************ //

    private List<WebElement> warningMessages() {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//md-error/span")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    public void fillCustomerInformation(String name, DataTable table) {

        List<List<String>> temp = table.raw();

        if (!temp.get(0).get(1).equals("{null}")) {
            this.nameInputField().sendKeys(name);
        } else System.out.println("INFO: Field \"Name\" set to null value");

        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Number", this.policyNumberInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Email", this.emailInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Phone Number", this.phoneNumberInputField());
        if (!temp.get(4).get(1).equals("{null}")) {
            this.languageDropdown().click();
            for (WebElement option : this.dropdownOptions()) {
                if (option.getText().equals(temp.get(4).get(1))) {
                    option.click();
                    break;
                }
            }
        } else System.out.println("INFO: Field \"Preferred Language\" set to NULL value");
        if (!temp.get(5).get(1).equals("{null}")) {
            this.provinceDropdown().click();
            for (WebElement option : this.dropdownOptions()) {
                if (option.getText().equals(temp.get(5).get(1))) {
                    option.click();
                    break;
                }
            }
        } else System.out.println("INFO: Field \"Province\" set to NULL value");
        Procedures.fillInputFieldBasedOnDataTable(temp, "Address 1", this.contactAddress1InputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Address 2", this.contactAddress2InputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "City", this.contactCityInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Postal Code", this.contactPostalCodeInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Effective Date", this.effectiveDateInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Policy Expiration Date", this.expirationDateInputField());
        if (!temp.get(12).get(1).equals("{null}")) {
            this.insurerDropdown().click();
            for (WebElement option : this.dropdownOptions()) {
                if (option.getText().equals(temp.get(12).get(1))) {
                    option.click();
                    break;
                }
            }
        } else System.out.println("INFO: Field \"Insurer\" set to NULL value");
        Procedures.fillInputFieldBasedOnDataTable(temp, "Brokerage", this.brokerageInputField());

    }

    public void fillVehicleInformation(DataTable table) {

        List<List<String>> temp = table.raw();

        Procedures.fillInputFieldBasedOnDataTable(temp, "Year", this.vehicleYearInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Make", this.vehicleMakeInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Model", this.vehicleModelInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "VIN", this.vinNumberInputField());

        this.addVehicleButton().click();
    }

    public void editVehicleInformation(String vin, DataTable table) {

        List<List<String>> temp = table.raw();

        this.editVehicleByVinButton(vin).get(0).click();
        Procedures.fillInputFieldBasedOnDataTable(temp, "Year", this.editVehicleYearInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Make", this.editVehicleMakeInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Model", this.editVehicleModelInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "VIN", this.editVinNumberInputField());
        this.saveButton().click();

    }

    public void removeVehicle(String vin) {
        this.removeVehicleByVinButton(vin).get(0).click();
    }

    public void moveVehicleUp(String vin) {
        this.moveUpVehicleByVinButton(vin).get(0).click();
    }

    public void moveVehicleDown(String vin) {
        this.moveDownVehicleByVinButton(vin).get(0).click();
    }

    public void fillBackText(DataTable table) {

        List<List<String>> temp = table.raw();

        Procedures.fillInputFieldBasedOnDataTable(temp, "Title", this.backTextTitleInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Text", this.backTextEntryInputField());

        this.addBackTextButton().click();
    }

    public void editBackTextInformation(String title, DataTable table) {

        List<List<String>> temp = table.raw();

        this.editBackTextByTitleButton(title).get(0).click();
        Procedures.fillInputFieldBasedOnDataTable(temp, "Title", this.editBackTextTitleInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Text", this.editBackTextEntryInputField());
        this.saveButton().click();

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

    public void saveDraft() {
        this.saveDraftButton().click();
    }

    public void next() {
        this.nextButton().click();
    }

    public void back() {
        this.backButton().click();
    }

    public void clearBackTextEntry() {
        this.clearBackTextEntryButton().click();
    }

    public void addBackTextEntry() {
        this.addBackTextButton().click();
    }

    public void fillSendPreview(DataTable table) {

        List<List<String>> temp = table.raw();
        Procedures.fillInputFieldBasedOnDataTable(temp, "Salutation", this.sendPreviewSalutationInputField());
        Procedures.fillInputFieldBasedOnDataTable(temp, "Customized Message", this.sendPreviewMessageInputField());

    }

    public void sendESlip() {
        this.sendESlipButton().click();
        try {Thread.sleep(5000);} catch (Exception e) {System.out.println("Sleep...");}
    }

    public int getNoOfVehiclesOnList() {
        try {
            return this.vehicleList().size() - 1;
        } catch (Exception e) {
            System.out.println("INFO: Vehicles list is empty!");
        }
        return 0;
    }

    public int getVehicleByVinPosition(String vin) {
        System.out.println("INFO: Current vehicle '" + vin +"' position: " + this.vehiclePositionOnTheList(vin).get(0).getText());
        return Integer.parseInt(this.vehiclePositionOnTheList(vin).get(0).getText());
    }

    public int getNoOfBackTextsOnList() {
        try {
            return this.backTextsList().size() - 3;
        } catch (Exception e) {
            System.out.println("INFO: Back texts list is empty!");
        }
        return 0;
    }

    public int getBackTextByTitlePosition(String title) {
        System.out.println("INFO: Current back test '" + title +"' position: " + this.backTextPositionOnTheList(title).get(0).getText());
        return Integer.parseInt(this.backTextPositionOnTheList(title).get(0).getText());
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

    public boolean verifyPageTitle(String title) {
        return this.pageTitle().getText().contains(title);
    }

    public boolean verifyWarningMessage(String message) {
        try {
            for (WebElement warning : this.warningMessages()) {
                if (warning.getText().contains(message)) return true;
            }
            System.out.println("INFO: Message '" + message + "' not found.");
        } catch (Exception e) {
            System.out.println("INFO: No warning messages are displayed!");
        }
        return false;
    }

    public boolean verifyIfVehicleWithVinIsListed(String vin) {
        try {
            return this.vehicleWithVinExistance(vin).size() > 0;
        } catch (Exception e) {
            System.out.println("INFO: Vehicle with vin '" + vin + "' not visible on list");
        }
        return false;
    }

    public boolean verifyIfBackTextWithTitleIsListed(String title) {
        try {
            return this.backTextWithTitleExistance(title).size() > 0;
        } catch (Exception e) {
            System.out.println("INFO: Back text with title'" + title + "' not visible on list");
        }
        return false;
    }

}
