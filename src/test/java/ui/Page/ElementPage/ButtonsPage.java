package ui.Page.ElementPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class ButtonsPage {
    private final SelenideElement doubleClickBtn = $("#doubleClickBtn");
    private final SelenideElement rightClickBtn = $("#rightClickBtn");

    public void clickOnDoubleClickBtn() {
        actions().doubleClick(doubleClickBtn).perform();
    }

    public boolean checkDoubleClickBtnMessage() {
        return $("#doubleClickMessage").isDisplayed();
    }

    public void clickOnRightClickBtn() {
        actions().contextClick(rightClickBtn).perform();
    }

    public boolean checkRightClickBtnMessage() {
        return $("#rightClickMessage").isDisplayed();
    }
}
