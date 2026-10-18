package tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleTestSteps {

    @Step("Открыть главную страницу")
    public void openTTG() {
        open("https://5e14.ttg.club/");
    }

    @Step("Клик по поисковому полю")
    public void searchFieldClick() {
        $(".home-search").click();
    }

    @Step("Ввести поисковой запрос")
    public void typeSearchQuery(String searchQuery) {
        $(".n-input__input-el").setValue(searchQuery);
    }

    @Step("Перейти на страницу через результат поиска")
    public void redirectToResultPage(String resultLink) {
        $(".search-modal__results").find("a[href=\"/" + resultLink + "\"]").click();
    }

    @Step("Проверить текст заголовка")
    public void checkHeader(String expectedHeader) {
        $(".content-layout__title").shouldHave(text(expectedHeader));
    }
}
