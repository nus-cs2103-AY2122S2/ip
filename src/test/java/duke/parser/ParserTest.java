package duke.parser;

import duke.DukeException;
import duke.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_ValidInputs_CreateCommands() {
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
            assertTrue(Parser.parse("list") instanceof ListCommand);
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
            assertTrue(Parser.parse("todo homework") instanceof AddCommand);
            assertTrue(Parser.parse("event birthday /at 1/1/22 12:00") instanceof AddCommand);
            assertTrue(Parser.parse("deadline assignment /by 10/01/2022 23:59") instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_InvalidCommand_ThrowDukeException() {
        try {
            Parser.parse("blah blah blah");
            fail();
        } catch (DukeException e) {
            assertEquals("Not an understood command", e.getMessage());
        }
    }

    @Test
    public void parse_IncorrectTimeFormat_ThrowDukeException() {
        try {
            Parser.parse("event birthday /at friday 3pm");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect time format", e.getMessage());
        }
    }
}
