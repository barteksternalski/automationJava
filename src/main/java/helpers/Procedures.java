package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

public class Procedures {

    private static int returnIndexOfStringElement(List<List<String>> myList, String sElement) {

        for (int i  = 0; i <myList.size(); i++) {
            if (myList.get(i).get(0).contains(sElement)) {
                return i;
            }
        }
        return -1;
    }

    public static void setFieldBaseOnDataTable(Consumer<String> myFunc, List<List<String>> ssDataTable, String sField) {

        if (returnIndexOfStringElement(ssDataTable, sField) != -1) myFunc.accept(ssDataTable.get(returnIndexOfStringElement(ssDataTable, sField)).get(1));

    }

    public static void fillInputFieldBasedOnDataTable(List<List<String>> rawDataTable, String fieldToFill, WebElement inputElement) {

        if (returnIndexOfStringElement(rawDataTable, fieldToFill) != -1) {
            if (!rawDataTable.get(returnIndexOfStringElement(rawDataTable, fieldToFill)).get(1).contains("{null}")) {
                inputElement.sendKeys(rawDataTable.get(returnIndexOfStringElement(rawDataTable, fieldToFill)).get(1));
            } else {
                System.out.println("INFO: Field \"" + fieldToFill + "\" set to NULL value");
            }
        } else {
            System.out.println("INFO: Field \"" + fieldToFill + "\" not included in data table");
        }

    }

    public static void selectDropdownValueBasedOnDataTable(List<List<String>> rawDataTable, String dropdownToSelect, WebElement dropdownElement, List<WebElement> dropdownOptions) {
        if (returnIndexOfStringElement(rawDataTable, dropdownToSelect) != -1) {
            if (!rawDataTable.get(returnIndexOfStringElement(rawDataTable, dropdownToSelect)).get(1).contains("{null}")) {
                dropdownElement.click();
                try {Thread.sleep(500);} catch(Exception e) {};
                for (WebElement option : dropdownOptions) {
                    if (option.getText().equals(dropdownToSelect)) {
                        option.click();
                    } else {
                        System.out.println("INFO: Field \"" + dropdownToSelect + "\" not included in data table");
                    }
                }
            } else {
                System.out.println("INFO: Field \"" + dropdownToSelect + "\" set to NULL value");
            }
        } else {
            System.out.println("INFO: Field \"" + dropdownToSelect + "\" not included in data table");
        }
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void pasteTextWithRobot() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }



}
