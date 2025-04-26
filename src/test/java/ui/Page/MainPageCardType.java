package ui.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainPageCardType {
    ELEMENTS("Elements"),
    FORMS("Forms"),
    INTERACTIONS("Interactions"),
    WIDGETS("Widgets");

    private final String title;
}
