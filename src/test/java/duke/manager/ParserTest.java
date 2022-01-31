package duke.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void testParse() throws Exception {
        Parser parser = new Parser();

        //test case 1
        assertTrue(parser.parse("list") instanceof ListCommand);

        //test case 2
        assertTrue(parser.parse("bye") instanceof ExitCommand);

        //test case 3
        try {
            parser.parse("mark");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please choose a task number", e.getMessage());
        }

        //test case 4
        try {
            parser.parse("todo");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty", e.getMessage());
        }
    }
}
