package ui.Page.ElementPage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ElementPageMenuListType {
    TEXT_BOX("Text Box"),
    CHECK_BOX("Check Box"),
    BUTTONS("Buttons"),
    LINKS("Links");

    private final String title;

    public String toLocator() {
        return switch (this) {
            case TEXT_BOX -> "item-0";
            case CHECK_BOX -> "item-1";
            case BUTTONS -> "item-4";
            case LINKS -> "item-5";
        };
    }
}
