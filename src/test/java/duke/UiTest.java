package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void validWelcomeMessage() {
        String expected = "Welcome to Duke, your personal Task assistant!\n";
        String output = ui.createWelcomeMessage();
        assertEquals(expected, output);
    }
}
