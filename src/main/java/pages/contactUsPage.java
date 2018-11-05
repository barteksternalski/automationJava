package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.text.MessageFormat;

public class contactUsPage extends basePage {

    public contactUsPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // *************************** //
    //           elements          //
    // *************************** //
    private WebElement buttonByTypeVisibility(String buttonType) {
        return getElement(ExpectedConditions.visibilityOfElementLocated(By.xpath(MessageFormat.format("//button[@type=''{0}'']", buttonType))));
    }

    private WebElement buttonByType(String buttonType) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//button[@type=''{0}'']", buttonType))));
    }

    private WebElement errorMessageForGivenField(String givenField) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[contains(@class, ''FieldWrap'')][descendant::label[@for=''{0}'']]/div[@class=''mktoError'']", givenField))));
    }

    private WebElement inputFieldByLabel(String label) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[contains(@class, ''FieldWrap'')][descendant::label[@for=''{0}'']]/input", label))));
    }

    private WebElement dropdownByLabel(String label) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[contains(@class, ''FieldWrap'')][descendant::label[@for=''{0}'']]/select", label))));
    }

    private WebElement textAreaByLabel(String label) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[contains(@class, ''FieldWrap'')][descendant::label[@for=''{0}'']]/textarea", label))));
    }

    // *************************** //
    //            methods          //
    // *************************** //

    public boolean isButtonActive(String buttonType) {
        return buttonByTypeVisibility(buttonType).isEnabled();
    }

    public void clickButton(String buttonType) {
        buttonByType(buttonType).click();
    }

    public void sendKeysToInputField(String fieldType, String text) {
        inputFieldByLabel(fieldType).clear();
        inputFieldByLabel(fieldType).sendKeys(text);
    }

    public void selectValueFromDropdown(String fieldType, String text) {
        Select dropdown = new Select(dropdownByLabel(fieldType));
        dropdown.selectByVisibleText(text);
    }

    public void sendKeysToTextarea(String fieldType, String text) {
        textAreaByLabel(fieldType).clear();
        textAreaByLabel(fieldType).sendKeys(text);
    }

    public void fillFormField(String formField, String value)
    {
        switch (formField)
        {
            case "FirstName":
            case "LastName":
            case "Email":
            case "Company":
                sendKeysToInputField(formField, value);
                break;
            case "Country":
            case "Avanade_Functional_Role":
            case "Avanade_Job_Role":
            case "Avanade_Relationship":
                selectValueFromDropdown(formField, value);
                break;
            case "Avanade_Website_ContactUs_Request":
                sendKeysToTextarea(formField, value);
                break;
            default:
                System.out.println(MessageFormat.format("No such field: {0}", formField));
                break;
        }
    }

    public String getErrorMessageForGivenField(String field) {
        String message;
        try {
            message = errorMessageForGivenField(field).getText();
        } catch (Exception e) {
            message = "";
        }

        return message;
    }

}
