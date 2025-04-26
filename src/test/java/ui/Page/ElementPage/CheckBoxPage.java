package ui.Page.ElementPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;

public class CheckBoxPage {
    private final SelenideElement checkbox = $(".rct-checkbox");

    public void clickOnCheckbox() {
        checkbox.click();
    }

    public void checkForSuccessMessage() {
        $("#result").$$("span.text-success").shouldHave(sizeGreaterThan(0));
    }
}
