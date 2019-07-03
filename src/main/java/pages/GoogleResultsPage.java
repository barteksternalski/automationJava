package pages;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;

public class GoogleResultsPage {

    private ElementsCollection results = $$("#ires .g"); //$$ = findAll, can use both

    public void checkResultsSizeIsAtLeast(int expectedSize) {
        results.shouldHave(sizeGreaterThan(expectedSize));
    }

    public void checkResultHasTest(int index, String expectedText) {
        results.get(index).shouldHave(text(expectedText));
    }

}
