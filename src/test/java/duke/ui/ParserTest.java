package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.WrongInputException;

public class ParserTest {

    @Test
    public void parseCommand_invalidInput_exceptionThrown() {
        try {
            assertEquals("remove", new Parser().parseCommand("remove"));
            fail();
        } catch (WrongInputException e) {
            assertEquals("D: D: D: I don't understand the input D: D: D:", e.getMessage());
        }
    }

    @Test
    public void parseNumericalDescription_validInput_success() {
        try {
            assertEquals(1, new Parser().parseNumericalDescription("unmark 2", "unmark", 3));
        } catch (WrongInputException e) {
            fail();
        }
    }

    @Test
    public void parseFormatDescription_validInput_success() {
        try {
            assertEquals("Thursday", new Parser().parseFormatDescription(
                    "deadline do this week iP /by Thursday", "deadline", "/by")[1]);
        } catch (Exception e) {
            fail();
        }
    }
}
