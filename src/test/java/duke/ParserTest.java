package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_blankTodoDescription_exceptionThrown() {
        try {
            Parser.parse("todo       ", 3);
        } catch (DukeException e) {
            assertEquals("Please enter a description for the todo task.", e.getMessage());
        }
    }
    @Test
    public void parse_deadlineDateMissing_exceptionThrown() {
        try {
            Parser.parse("deadline this           ", 3);
        } catch (DukeException e) {
            assertEquals("Please specify a deadline task as\n"
                    + "deadline [description] /by [date in yyyy-mm-dd format].", e.getMessage());
        }
    }
    @Test
    public void parse_invalidDateFormat_exceptionThrown() {
        try {
            Parser.parse("deadline this /by 12 Feb 2022", 3);
        } catch (DukeException e) {
            assertEquals("Please specify dates in the yyyy-mm-dd format", e.getMessage());
        }
    }

    @Test
    public void parse_invalidMarkIndex_exceptionThrown() {
        try {
            Parser.parse("mark 4", 3);
        } catch (DukeException e) {
            assertEquals("Please specify a valid task index.", e.getMessage());
        }
    }
}
