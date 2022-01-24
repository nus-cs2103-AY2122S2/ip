package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_blankTodoDescription_ExceptionThrown() {
        try {
            Parser.parse("todo       ", 3);
        } catch (DukeException e) {
            assertEquals("Please enter a description for the todo task.", e.getMessage());
        }
    }
    @Test
    public void parse_deadlineDateMissing_ExceptionThrown() {
        try {
            Parser.parse("deadline this           ", 3);
        } catch (DukeException e) {
            assertEquals("Please specify a deadline task as\n" +
                    "deadline [description] /by [date in yyyy-mm-dd format].", e.getMessage());
        }
    }
    @Test
    public void parse_invalidDateFormat_ExceptionThrown() {
        try {
            Parser.parse("deadline this /by 12 Feb 2022", 3);
        } catch (DukeException e) {
            assertEquals("Please specify dates in the yyyy-mm-dd format", e.getMessage());
        }
    }

    @Test
    public void parse_invalidMarkIndex_ExceptionThrown() {
        try {
            Parser.parse("mark 4", 3);
        } catch (DukeException e) {
            assertEquals("Please specify a valid task index.", e.getMessage());
        }
    }
}
