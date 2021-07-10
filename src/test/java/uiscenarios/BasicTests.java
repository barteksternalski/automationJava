package uiscenarios;

import static com.codeborne.selenide.Selenide.open;
import static tags.SuiteNames.UI_TEST;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import pages.GoogleResultsPage;
import pages.GoogleSearchPage;

@Execution(ExecutionMode.CONCURRENT)
public class BasicTests extends BaseUiTest {

    @Test
    @Tag(UI_TEST)
    public void test() {
        open("http://google.com");
        new GoogleSearchPage().searchFor("banan");
        GoogleResultsPage results = new GoogleResultsPage();
        results.checkResultsSizeIsAtLeast(1);
        results.checkResultHasTest(0, "banan");
    }

    @Test
    @Tag(UI_TEST)
    public void test2() {
        open("http://onet.com");
        new GoogleSearchPage().searchFor("banan");
        GoogleResultsPage results = new GoogleResultsPage();
        results.checkResultsSizeIsAtLeast(1);
        results.checkResultHasTest(0, "banan");
    }

}
