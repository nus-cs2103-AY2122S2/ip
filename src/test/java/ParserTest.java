import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ToggleCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class ParserTest {
    /**
     * Test to determine if {@link Parser#parseInt(String)} works as intended.
     */
    @Test
    public void parseIntTest() {
        try {
            assertEquals(-1, Parser.parseInt("-1"));
            assertEquals(0, Parser.parseInt("0"));
            assertEquals(99, Parser.parseInt("99"));
            assertEquals(-1, Parser.parseInt("NaN")); // trigger exception
        } catch (DukeException e) {
            assertEquals("Please provide the item NUMBER to remove.", e.getMessage());
        }
    }

    /**
     * Test to determine if {@link Parser#parseCommand(String)} works as intended.
     */
    @Test
    public void parseCommandTest() throws DukeException {
        String[] commands = {
            "exit", "list", "mark 0", "unmark 0", "todo todo",
            "event e /at 29/01/2022 16:00", "deadline d /by 29/01/2022 16:00",
            "remove 0", "delete 0", "invalid"
        };

        assertTrue(Parser.parseCommand(commands[0]) instanceof ExitCommand);
        assertTrue(Parser.parseCommand(commands[1]) instanceof ListCommand);
        assertTrue(Parser.parseCommand(commands[2]) instanceof ToggleCommand);
        assertTrue(Parser.parseCommand(commands[3]) instanceof ToggleCommand);
        assertTrue(Parser.parseCommand(commands[4]) instanceof AddCommand);
        assertTrue(Parser.parseCommand(commands[5]) instanceof AddCommand);
        assertTrue(Parser.parseCommand(commands[6]) instanceof AddCommand);
        assertTrue(Parser.parseCommand(commands[7]) instanceof DeleteCommand);
        assertTrue(Parser.parseCommand(commands[8]) instanceof DeleteCommand);
        assertTrue(Parser.parseCommand(commands[9]) instanceof InvalidCommand);
    }

    /**
     * Test to determine if {@link Parser#parseStringToTask(String)} works as intended.
     */
    @Test
    public void parseStringToTaskTest() {
        String[] inputs = {
            "T | 1 | read book",
            "D | 0 | return book | 06/06/2022 15:00",
            "E | 0 | project meeting | 06/08/2022 14:00",
            "X | 3 | false value"
        };

        try {
            assertTrue(Parser.parseStringToTask(inputs[0]) instanceof ToDo);
            assertTrue(Parser.parseStringToTask(inputs[1]) instanceof Deadline);
            assertTrue(Parser.parseStringToTask(inputs[2]) instanceof Event);
            assertNotNull(Parser.parseStringToTask(inputs[3]));
        } catch (DukeException e) {
            assertEquals("Invalid keyword from source file.", e.getMessage());

        }
    }
}
