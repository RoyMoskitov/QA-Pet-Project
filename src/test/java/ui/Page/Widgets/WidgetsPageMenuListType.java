package ui.Page.Widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WidgetsPageMenuListType {
    ACCORDIAN("Accordian"),
    AUTO_COMPLETE("Auto Complete"),
    DATE_PICKER("Date Picker"),
    SELECT_MENU("Select Menu"),;

    private final String title;

    public String toLocator() {
        return switch (this) {
            case ACCORDIAN -> "item-0";
            case AUTO_COMPLETE -> "item-1";
            case DATE_PICKER -> "item-2";
            case SELECT_MENU -> "item-8";
        };
    }
}