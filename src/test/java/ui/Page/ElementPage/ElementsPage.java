package ui.Page.ElementPage;

import com.codeborne.selenide.ElementsCollection;
import ui.Page.MainPageCardType;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class ElementsPage {

    public ElementsCollection getElementList() {
        return $$(".element-group")
                .findBy(text("Elements"))
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

    public void clickOnMenuList(ElementPageMenuListType menuListType) {
        getElementList()
                .findBy(id(menuListType.toLocator()))
                .click();
    }
}

