package ui;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.Page.*;
import ui.Page.ElementPage.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("DemoQA Tests")
@Owner("RoyMoskitov")
@Tag("ui")
public class ElementsTests extends BaseTest {
    public static final String BASE_URL = "https://demoqa.com/";

    MainPage mainPage;
    ElementsPage elementsPage;

    @BeforeEach
    public void setUpEachTest() {
        mainPage = new MainPage(BASE_URL);
        elementsPage = new ElementsPage();
    }

    @Test
    void shouldOpenElementsPage_WhenClickOnElementsCard() {
        String expectedUrlPart = "/elements";

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);

        String currentUrl = WebDriverRunner.url();
        assertTrue(currentUrl.contains(expectedUrlPart));
    }

    @ParameterizedTest
    @CsvSource({
            "Dima, dima@gmail.com, Moscow, Vladivostok",
            "Anna, anna@mail.ru, Kazan, Novosibirsk",
            "Veronika, ver@gmail.com, Vladivostok, Vladivostok"
    })
    void shouldOpenTextBoxPage_AndAcceptCorrectValues(
            String expectedName, String expectedEmail,
            String expectedCurrentAddress, String expectedPermanentAddress) {

        TextBoxPage textBoxPage = new TextBoxPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.TEXT_BOX);
        textBoxPage.fillForm(expectedName, expectedEmail, expectedCurrentAddress, expectedPermanentAddress);
        textBoxPage.submitForm();

        textBoxPage.checkOutputHasFields(expectedName, expectedEmail,
                expectedCurrentAddress, expectedPermanentAddress);
    }


    @Test
    public void shouldOpenTextCheckBoxPage_AndShowNewText() {
        CheckBoxPage checkBoxPage = new CheckBoxPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.CHECK_BOX);
        checkBoxPage.clickOnCheckbox();

        checkBoxPage.checkForSuccessMessage();
    }

    @Test
    public void shouldOpenButtonsPage_AndShowDoubleClickMessage() {
        ButtonsPage buttonsPage = new ButtonsPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.BUTTONS);

        assertFalse(buttonsPage.checkDoubleClickBtnMessage());
        buttonsPage.clickOnDoubleClickBtn();
        assertTrue(buttonsPage.checkDoubleClickBtnMessage());
    }

    @Test
    public void shouldOpenButtonsPage_AndShowRightClickMessage() {
        ButtonsPage buttonsPage = new ButtonsPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.BUTTONS);

        assertFalse(buttonsPage.checkRightClickBtnMessage());
        buttonsPage.clickOnRightClickBtn();
        assertTrue(buttonsPage.checkRightClickBtnMessage());
    }

    @Test
    public void shouldOpenLinksPage_AndMoveToHomePage() {
        LinksPage linksPage = new LinksPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.LINKS);

        linksPage.clickOnDynamicLink();
        List<String> tabs = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());
        WebDriverRunner.getWebDriver().switchTo().window(tabs.get(1));

        assertEquals(BASE_URL, WebDriverRunner.url());
    }

    @Test
    public void shouldOpenLinksPage_AndHasCorrectResponse() {
        LinksPage linksPage = new LinksPage();

        mainPage.clickOnCard(MainPageCardType.ELEMENTS);
        elementsPage.clickOnMenuList(ElementPageMenuListType.LINKS);
        linksPage.clickOnMovedLink();

        linksPage.checkForMovedResponse();
    }
}
