package ui.Page.ElementPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LinksPage {
    private final SelenideElement dynamicLink = $("#simpleLink");
    private final SelenideElement movedLink = $("#moved");

    public void clickOnDynamicLink() {
        dynamicLink.click();
    }

    public void clickOnMovedLink() {
        movedLink.click();
    }

    public void checkForMovedResponse() {
        $("#linkResponse").shouldHave(text("301"));
    }
}
