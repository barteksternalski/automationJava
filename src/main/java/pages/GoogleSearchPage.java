package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage {

    private SelenideElement searchField = $(byName("q")); //$ = find, can use both

    public void searchFor(String text) {
        searchField.val(text).pressEnter();
    }

}
