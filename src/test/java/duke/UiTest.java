package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void uiTestWelcome_valid_success() {
        Ui ui = new Ui();
        String lines = ui.getWelcomeMessage();
        assertEquals("Hello! I'm Dusk\n     What can I do for you?", lines);
    }
}
