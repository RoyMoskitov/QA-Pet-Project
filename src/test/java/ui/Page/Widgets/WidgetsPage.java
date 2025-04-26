package ui.Page.Widgets;

import com.codeborne.selenide.ElementsCollection;
import ui.Page.MainPageCardType;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class WidgetsPage {
    public ElementsCollection getWidgetsList() {
        return $$(".element-group")
                .findBy(text("Widgets"))
                .$$(".btn");
    }

    public ElementsCollection getExactPagesList(MainPageCardType groupType) {
        return $$(".element-group")
                .findBy(text(groupType.getTitle()))
                .$$(".btn");
    }

    public void clickOnCard(MainPageCardType groupType) {
        $$(".element-group")
                .findBy(text(groupType.getTitle()))
                .click();
    }

    public void clickOnMenuList(WidgetsPageMenuListType menuListType) {
        getWidgetsList()
                .findBy(id(menuListType.toLocator()))
                .click();
    }
}
