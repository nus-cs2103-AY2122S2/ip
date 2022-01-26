package duke.junit;

import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseWrongInput() {
        try {
            Parser.parseInput("hihih", new TaskList(), new Storage("./data/duke.txt"));
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Oh no! I fear I don't understand! Try again!\n");
        }
    }

    @Test
    public void parseTest1() throws DukeException {
        assertTrue(Parser.parseInput("event one /at 2020-01-01 00:01", new TaskList(),
                new Storage("./data/duke.txt")) instanceof AddEventCommand);
    }

    @Test
    public void parseTest2() throws DukeException {
        assertTrue(Parser.parseInput("todo sell eggs", new TaskList(),
                new Storage("./data/duke.txt")) instanceof AddTodoCommand);
    }

}
