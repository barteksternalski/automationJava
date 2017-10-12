package pages;

import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GlobalConfCarriers extends BasePage {

    public GlobalConfCarriers(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> carrierByNameCheckbox(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//div[contains(@class,'container')]")));
    }

    private List<WebElement> carrierByName(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]")));
    }

    private WebElement newButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New']")));
    }

    private WebElement editButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
    }

    private WebElement deleteButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']")));
    }

    // ****** new carrier form ******** //

    private WebElement nameInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("name")));
    }

    private WebElement addressInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("address")));
    }

    private WebElement codeInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("code")));
    }

    private WebElement detailsInputField() {
        return getElement(ExpectedConditions.elementToBeClickable(By.id("details")));
    }

    private WebElement cancelButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']")));
    }

    private WebElement saveButton() {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
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

    public void addNewCarrier(DataTable table) {

        List<List<String>> temp = table.raw();

        this.newButton().click();

        if (!temp.get(0).get(1).equals("{null}")) this.nameInputField().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, temp.get(0).get(1));
        if (!temp.get(1).get(1).equals("{null}")) this.addressInputField().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, temp.get(1).get(1));
        if (!temp.get(2).get(1).equals("{null}")) this.codeInputField().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, temp.get(2).get(1));
        if (!temp.get(3).get(1).equals("{null}")) this.detailsInputField().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, temp.get(3).get(1));

        this.saveButton().click();
    }

    public void edit() {
        this.editButton().click();
    }

    public void delete() {
        this.deleteButton().click();
    }

    public void selectCarrierByName(String name) {
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.carrierByNameCheckbox(name).size() > 0) {
                    this.carrierByNameCheckbox(name).get(0).click();
                    break;
                }
            } catch(Exception e) {
                System.out.println("INFO: Carrier not displayed on current page");
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

    public boolean verifyIfCarrierInDisplayedOnList(String name) {
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.carrierByName(name).size() > 0) {
                    System.out.println("INFO: Carrier found!");
                    return true;
                }
            } catch(Exception e) {
                System.out.println("INFO: Carrier not displayed on current page");
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
