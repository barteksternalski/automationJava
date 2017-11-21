package helpers;

import org.joda.time.DateTime;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.Base64;

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
                inputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, rawDataTable.get(returnIndexOfStringElement(rawDataTable, fieldToFill)).get(1));
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
                try {Thread.sleep(500);} catch(Exception e) {System.out.println("Sleep...");}
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

    public static String generateRandomizedStringWithLength(int length) {
        String s = UUID.randomUUID().toString().replace("-", "_");
        length = length <= s.length() ? length : s.length();
        return s.substring(0, length);
    }

    public static String convertFileToBase64() {
        String base64Image = "";
        File file = new File("src/test/resources/testFiles/andromeda.jpg");
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    public static String convertStringToBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String generateDateWithCurrentMiliseconds() {
        DateTime time = new DateTime();
        return Integer.toString(time.getYear()) + Integer.toString(time.getMonthOfYear()) + Integer.toString(time.getDayOfMonth()) + Long.toString(time.getMillis()).substring(4);
    }

}
