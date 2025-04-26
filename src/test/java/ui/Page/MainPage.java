package ui.Page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Главная страница сайта DemoQA
 */
public class MainPage {
    public MainPage(String url) {
        Selenide.open(url);
    }

    public void clickOnCard(MainPageCardType cardType) {
        $$(".card").findBy(text(cardType.getTitle())).click();
    }
}
