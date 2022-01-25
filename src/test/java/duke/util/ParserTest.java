package duke.util;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseDelete_withTaskSpecified_success() throws DukeException {
        assertFalse(Parser.parse("delete 1").isExit());
    }

    @Test
    public void parseDelete_noTaskSpecified_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("delete"));
            fail();
        } catch (DukeException e) {
            assertEquals("Please specify a task number!", e.getMessage());
        }
    }

    @Test
    public void parseTodo_withDescription_success() throws DukeException {
        assertFalse(Parser.parse("todo homework").isExit());
    }

    @Test
    public void parseTodo_descriptionBlank_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("todo"));
            fail();
        } catch (DukeException e) {
            assertEquals("Oops, a todo description cannot be left empty!", e.getMessage());
        }
    }

    @Test
    public void parseDeadline_invalidDateTime_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("deadline send email /by 01/01/2022 1400"));
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date/time format.\n    " + "Please use the following format: yyyy-mm-dd HH:mm",
                    e.getMessage());
        }
    }
}
