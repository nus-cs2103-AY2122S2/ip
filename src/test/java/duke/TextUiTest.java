package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TextUiTest {
    private static final String DIVIDER = "===================================================";

    @Test
    public void testDivider() {
        TextUi ui = new TextUi();
        assertEquals(DIVIDER, ui.showDivider());
    }
}
