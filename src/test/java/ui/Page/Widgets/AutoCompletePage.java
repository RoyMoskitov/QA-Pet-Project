package ui.Page.Widgets;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AutoCompletePage {
    SelenideElement autoCompleteSingle = $("#autoCompleteSingleContainer input");
    SelenideElement autoCompleteMulti = $("#autoCompleteMultipleContainer input");
    SelenideElement autoCompleteMultipleContainer = $(".auto-complete__value-container--is-multi");
    SelenideElement autoCompleteSingleContainer =
            $(".auto-complete__value-container:not(.auto-complete__value-container--is-multi)");


    public void setAutoCompleteSingle(String text) {
        autoCompleteSingle.setValue(text);
    }

    public void setAutoCompleteMulti(String text) {
        autoCompleteMulti.setValue(text);
    }

    public void clickOnAutoCompleteOption(String text) {
        $$(".auto-complete__option").findBy(text(text)).shouldBe(visible).click();
    }

    public void checkAutoCompleteMultiIsFilled(String ... args) {
        for (String arg : args) {
            autoCompleteMultipleContainer.shouldHave(text(arg));
        }
    }

    public void checkAutoCompleteSingleIsFilled(String text) {
        autoCompleteSingleContainer.shouldHave(text(text));
    }
}

