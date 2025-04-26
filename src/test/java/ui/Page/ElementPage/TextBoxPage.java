package ui.Page.ElementPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {
    SelenideElement fullName = $("#userName");
    SelenideElement userEmail = $("#userEmail");
    SelenideElement currentAddress = $("#currentAddress");
    SelenideElement permanentAddress = $("#permanentAddress");
    SelenideElement submit = $("#submit");
    SelenideElement output = $(".border");

    public void fillForm(String fullNameVal, String userEmailVal,
                         String currentAddressVal, String permanentAddressVal) {
        fullName.setValue(fullNameVal);
        userEmail.setValue(userEmailVal);
        currentAddress.setValue(currentAddressVal);
        permanentAddress.setValue(permanentAddressVal);
    }

    public void submitForm() {
        submit.scrollIntoView(true).shouldBe(visible, enabled).click();
    }

    public void checkOutputHasFields(String name, String email, String currentAddress, String permanentAddress) {
        output.shouldBe(visible);

        output.$("#name").shouldHave(text("Name:" + name));
        output.$("#email").shouldHave(text("Email:" + email));
        output.$("#currentAddress").shouldHave(text("Current Address :" + currentAddress));
        output.$("#permanentAddress").shouldHave(text("Permananet Address :" + permanentAddress));
    }

}
