package ui.Page.Widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AccordianPage {
    SelenideElement secondCard = $(".card:nth-last-of-type(2)");
    SelenideElement firstCard = $(".card:first-child");

    public void clickOnFirstCard() {
        firstCard.shouldBe(visible, enabled).click();
    }

    public void clickOnSecondCard() {
        secondCard.shouldBe(visible, enabled).click();
    }

    public void checkFirstTextIsVisible() {
        firstCard.$("#section1Content").shouldBe(visible);
    }

    public void checkFirstTextIsNotVisible() {
        secondCard.$("#section1Content").shouldNotBe(visible);
    }
}
