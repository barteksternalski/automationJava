package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.MessageFormat;

public class searchResultsPage extends basePage {

    public searchResultsPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // *************************** //
    //           elements          //
    // *************************** //
    private WebElement numberOfResults(String resultsType) {
        return getElement(ExpectedConditions.elementToBeClickable(By.xpath(MessageFormat.format("//div[contains(@class,''search-blog'')]//a[contains(text(),''{0}'')]/span", resultsType))));
    }

    // *************************** //
    //            methods          //
    // *************************** //
    public int getNumberOfResults(String resultsType) {
        int noOfResults;
        try {
            noOfResults = Integer.parseInt(numberOfResults(resultsType).getText().replace("(", "").replace(")", ""));
        } catch(Exception e) {
            noOfResults = 0;
        }
        return noOfResults;
    }

}
