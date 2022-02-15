package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testFormatStartUpMessage() {
        Ui ui = new Ui();
        assertEquals("Hello I'm\n\n" + "\tDUKE\n\n" + "What can I do for you?\n", ui.formatStartUpMessage());
    }

    @Test
    public void testFormatInputMessage_nonNullInput_noException() {
        Ui ui = new Ui();
        assertEquals("This is a test.", ui.formatFeedbackMessage("This is a test."));
    }

    @Test
    public void testFormatInputMessage_nullInput_noException() {
        Ui ui = new Ui();
        assertEquals("", ui.formatFeedbackMessage(null));
    }
}
