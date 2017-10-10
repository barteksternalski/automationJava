package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListOfDrafts extends BasePage {

    public ListOfDrafts(WebDriver driver, int timeOut) {
        super(driver, timeOut);
    }

    // ******************************** //
    //                                  //
    //              ELEMENTS            //
    //                                  //
    // ******************************** //

    private List<WebElement> eSlipByNameCheckbox(String name) {
        return getElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[descendant::td[contains(text(),'" + name + "')]]//div[contains(@class,'container')]")));
    }

    private List<WebElement> eSlipByNameState(String name) {
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
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.eSlipByNameCheckbox(name).size() > 0) {
                    this.eSlipByNameCheckbox(name).get(0).click();
                    break;
                }
            } catch(Exception e) {
                System.out.println("INFO: user not displayed on current page");
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
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.eSlipByNameState(name).size() > 0) {
                    return this.eSlipByNameState(name).get(0).getText();
                }
            } catch(Exception e) {
                System.out.println("INFO: eSlip not displayed on current page");
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
        return "INFO: ESlip with '"+ name + "' Name not found on list.";
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
        boolean contFlag = true;
        while(contFlag) {
            try {
                Thread.sleep(1000);
                if (this.eSlipByName(name).size() > 0) {
                    System.out.println("INFO: eSlip found!");
                    return true;
                }
            } catch(Exception e) {
                System.out.println("INFO: eSlip not displayed on current page");
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
