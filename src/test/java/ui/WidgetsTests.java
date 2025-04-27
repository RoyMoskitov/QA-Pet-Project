package ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.Page.MainPage;
import ui.Page.MainPageCardType;
import ui.Page.Widgets.*;
import ui.Page.Widgets.WidgetsPage;

import java.util.Arrays;
import java.util.List;

@Epic("DemoQA Tests")
@Owner("RoyMoskitov")
@Tag("ui")
public class WidgetsTests extends BaseTest{
    public static final String BASE_URL = "https://demoqa.com/";

    MainPage mainPage;
    WidgetsPage widgetsPage;

    @BeforeEach
    public void setUpEachTest() {
        mainPage = new MainPage(BASE_URL);
        widgetsPage = new WidgetsPage();
    }

    @Feature("Widgets - Accordion")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Accordion opens on click")
    @Description("Проверка открытия первой секции аккордеона")
    @Test
    void shouldOpenAccordianPage_AndShowCorrectTab() {
        AccordianPage accordianPage = new AccordianPage();

        mainPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnMenuList(WidgetsPageMenuListType.ACCORDIAN);

        accordianPage.clickOnFirstCard();
        accordianPage.checkFirstTextIsVisible();

        accordianPage.clickOnSecondCard();
        accordianPage.checkFirstTextIsNotVisible();
    }


    @Feature("Widgets - Auto-complete")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Auto-complete offers single correct variants")
    @Description("Проверка предложений автокомплита")
    @Test
    void shouldOpenAutoCompletePage_AndSaveCorrectSingleValue() {
        AutoCompletePage autoCompletePage = new AutoCompletePage();

        mainPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnMenuList(WidgetsPageMenuListType.AUTO_COMPLETE);

        autoCompletePage.setAutoCompleteSingle("Gr");
        autoCompletePage.clickOnAutoCompleteOption("Green");
        autoCompletePage.checkAutoCompleteSingleIsFilled("Green");
    }

    @Feature("Widgets - Auto-complete")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Auto-complete offers multiple correct variants")
    @Description("Проверка предложений автокомплита")
    @Test
    void shouldOpenAutoCompletePage_AndSaveCorrectMultipleValues() {
        AutoCompletePage autoCompletePage = new AutoCompletePage();

        mainPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnMenuList(WidgetsPageMenuListType.AUTO_COMPLETE);

        autoCompletePage.setAutoCompleteMulti("Gr");
        autoCompletePage.clickOnAutoCompleteOption("Green");
        autoCompletePage.setAutoCompleteMulti("Blue");
        autoCompletePage.clickOnAutoCompleteOption("Blue");

        autoCompletePage.checkAutoCompleteMultiIsFilled("Blue", "Green");
    }

    @Feature("Widgets - Date Picker")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Date Picker creates correct data")
    @Description("Проверка правильного выбора даты")
    @Test
    void shouldOpenDatePickerPage_AndSelectCorrectDate() {
        DatePickerPage datePickerPage = new DatePickerPage();
        String date = "Tuesday, November 4th, 2025";

        mainPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnCard(MainPageCardType.WIDGETS);
        widgetsPage.clickOnMenuList(WidgetsPageMenuListType.DATE_PICKER);

        String [] strs = date.split(", ");
        datePickerPage.clickOnReactInputContainer();

        String monthDay = strs[1];
        String year = strs[2];

        String[] monthDayParts = monthDay.split(" ");
        String monthName = monthDayParts[0];
        String dayRaw = monthDayParts[1];

        datePickerPage.selectMonth(Month.valueOf(monthName));
        datePickerPage.selectYear(Integer.parseInt(year));
        datePickerPage.selectExactDay(date);

        String day = dayRaw.replaceAll("(st|nd|rd|th)", "");

        int monthNumber = monthNameToNumber(monthName);

        String expectedDate = String.format("%02d/%02d/%s", monthNumber, Integer.parseInt(day), year);

        datePickerPage.checkForCorrectDate(expectedDate);
    }

    public static int monthNameToNumber(String monthName) {
        List<String> months = Arrays.stream(Month.values()).map(Enum::name).toList();
        for (int i = 0; i < months.size(); i++) {
            if (months.get(i).equalsIgnoreCase(monthName)) {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Unknown month: " + monthName);
    }
}
