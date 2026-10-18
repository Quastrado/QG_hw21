package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class SimpleTest extends TestBase {

    private static final String QUERY = "Классы";
    private static final String LINK = "classes";
    private static final String HEADER = "Классы";

    @Test
    public void redirectToResultPageTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        SimpleTestSteps testSteps = new SimpleTestSteps();
        testSteps.openTTG();
        testSteps.searchFieldClick();
        testSteps.typeSearchQuery(QUERY);
        testSteps.redirectToResultPage(LINK);
        testSteps.checkHeader(HEADER);
    }

}
