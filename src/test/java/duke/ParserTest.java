package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_validByeCommand_success() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c.isExit());
        } catch (DukeException e) {

        }
    }

    @Test
    public void parse_invalidToDoCommand_exceptionThrown() {
        try {
            assertEquals("foo", Parser.parse("todo"));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
