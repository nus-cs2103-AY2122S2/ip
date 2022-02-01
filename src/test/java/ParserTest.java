import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Test Parser class for JUnit testing
 */
public class ParserTest {
    /**
     * Test if the error outputted is correct when an invalid command is inputted
     */
    @Test
    public void parseWrongInput() {
        try {
            Parser.parseInput("hihih", new TaskList(), new Storage("./data/duke.txt"));
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Oh no! I fear I don't understand! Try again!\n");
        }
    }

    /**
     * Test if the correct type of task is called and executed.
     *
     * @throws DukeException when task cannot be added to list
     */
    @Test
    public void parseTest1() throws DukeException {
        assertTrue(Parser.parseInput("event one /at 2020-01-01 00:01", new TaskList(),
                new Storage("./data/duke.txt")) instanceof AddEventCommand);
    }

    /**
     * Test if the correct type of task is called and executed.
     *
     * @throws DukeException when task cannot be added to list
     */
    @Test
    public void parseTest2() throws DukeException {
        assertTrue(Parser.parseInput("todo sell eggs", new TaskList(),
                new Storage("./data/duke.txt")) instanceof AddTodoCommand);
    }

}
