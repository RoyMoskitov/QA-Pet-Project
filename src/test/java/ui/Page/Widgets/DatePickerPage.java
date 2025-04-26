package ui.Page.Widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Year;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePickerPage {
    SelenideElement reactInputContainer = $(".react-datepicker__input-container");

    public void clickOnReactInputContainer() {
        reactInputContainer.click();
    }

    public void selectMonth(Month month) {
        $(".react-datepicker__month-select").selectOption(month.toString());
    }

    public void selectYear(Integer year) {
        $(".react-datepicker__year-select").selectOption(year.toString());
    }

    public void selectDay(Integer day) {
        $$(".react-datepicker__day")
                .findBy(text(day.toString()))
                .click();
    }

    //ex: Tuesday, November 4th, 2025
    public void selectExactDay(String date) {
        $("[aria-label='Choose " + date + "']").click();
    }

    //ex: 11/17/2025
    public void checkForCorrectDate(String date) {
        reactInputContainer.$("input").shouldHave(Condition.value(date));
    }
}
