package pages;

import cucumber.api.DataTable;
import helpers.Procedures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CreateUser extends BasePage {

    public CreateUser(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> userTypeRadioButton() {
        return getElements(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='userType']")));
    }

    private WebElement userIdInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("userId")));
    }

    private WebElement nameInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("name")));
    }

    private WebElement emailInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("email")));
    }

    private WebElement organizationUserIdInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("organizationUserId")));
    }

    private List<WebElement> organizationTypeRadioButton() {
        return getElements(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@name='organizationType']")));
    }

    private WebElement csioNetIdInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("csioNetId")));
    }

    private WebElement chooseFileButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
    }

    private WebElement accessToModulesCheckbox(String module) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//tr[descendant::td[.='" + module + "']]//input")));
    }

    private WebElement carrierOrganizationDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("carrierId")));
    }

    private WebElement brokerageOrganizationDropdown() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("brokerId")));
    }

    private WebElement saveButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
    }

    private WebElement cancelButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']")));
    }

    // ******************************** //
    //                                  //
    //              ACTIONS             //
    //                                  //
    // ******************************** //

    private void selectUserType(String type) {

        switch (type) {
            case "Organization":
                this.userTypeRadioButton().get(0).click();
                break;
            case "User":
                this.userTypeRadioButton().get(1).click();
                break;
            case "CSIO Admin":
                this.userTypeRadioButton().get(2).click();
                break;
            default:
                System.out.println("INFO: There is no '" + type + "' user");
                break;
        }

    }

    private void selectOrganizationType(String type) {

        switch(type) {
            case "Carrier":
                this.organizationTypeRadioButton().get(0).click();
                break;
            case "Brokerage":
                this.organizationTypeRadioButton().get(1).click();
                break;
            default:
                System.out.println("INFO: There is no '" + type + "' organization");
                break;
        }
    }

    private void chooseFile(String filePath) throws Exception {

        File file = new File(filePath);
        this.chooseFileButton().click();
        Thread.sleep(1000);
        Procedures.setClipboardData(file.getAbsolutePath());
        Procedures.pasteTextWithRobot();
        Thread.sleep(1000);

    }

    private void selectModules(String listOfModules) {

        List<String> modules = Arrays.asList(listOfModules.split(","));
        for (String temp : modules) {
            accessToModulesCheckbox(temp).click();
        }
    }

    public void createNewUser(DataTable table, String userId) throws Exception {

        List<List<String>> temp = table.raw();

        if (!temp.get(0).get(1).equals("{null}")) this.selectUserType(temp.get(0).get(1));
        if (!temp.get(1).get(1).equals("{null}")) this.userIdInputField().sendKeys(userId);
        if (!temp.get(2).get(1).equals("{null}")) this.nameInputField().sendKeys(temp.get(2).get(1));
        if (!temp.get(3).get(1).equals("{null}")) this.emailInputField().sendKeys(temp.get(3).get(1));
        if (!temp.get(4).get(1).equals("{null}")) this.organizationUserIdInputField().sendKeys(temp.get(4).get(1));
        if (!temp.get(5).get(1).equals("{null}")) this.selectOrganizationType(temp.get(5).get(1));
        if (!temp.get(6).get(1).equals("{null}")) this.csioNetIdInputField().sendKeys(temp.get(6).get(1));
        if (!temp.get(7).get(1).equals("{null}")) selectElementFromDropdown(carrierOrganizationDropdown(), temp.get(7).get(1));
        if (!temp.get(8).get(1).equals("{null}")) selectElementFromDropdown(brokerageOrganizationDropdown(), temp.get(8).get(1));
        if (!temp.get(9).get(1).equals("{null}")) this.chooseFile(temp.get(9).get(1));
        if (!temp.get(10).get(1).equals("{null}")) this.selectModules(temp.get(10).get(1));

        this.saveButton().click();
    }

    // ******************************** //
    //                                  //
    //          VERIFICATIONS           //
    //                                  //
    // ******************************** //

}
