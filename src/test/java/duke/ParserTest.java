package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.BingChillingException;
import duke.ui.MessageUi;

public class ParserTest {
    private MessageUi messageUi;

    @Test
    public void testInvalidCommand() {
        try {
            Parser.parse("invalid input");
        } catch (BingChillingException err) {
            assertEquals("Bing Chilling does not recognise the command", err.getMessage());
        }
    }

    @Test
    public void testEmptyCommand() {
        try {
            Parser.parse("");
        } catch (BingChillingException err) {
            assertEquals("Bing Chilling does not recognise the command",
                    err.getMessage());
        }
    }

    @Test
    public void testInvalidFormatCommand() {
        try {
            Parser.parse("todo three");
        } catch (BingChillingException err) {
            assertEquals("Bing Chilling does not recognise the command format",
                    err.getMessage());
        }
    }
}
