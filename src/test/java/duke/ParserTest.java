package duke;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ParserTest class that tests the action of Parsing user
 * input to do something
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-4
 */

public class ParserTest {
    TaskList tasks = new TaskList();
    Storage storage = new Storage("data/duke.txt", tasks);

    /** Runs a test to create a ToDo Task by parsing user input */
    @Test
    public void parserToDoTest_createToDoTask_success() throws DukeException, IOException {
        String expected = "Added Todo: T - 0 - Join Club";
        Parser parser = new Parser(tasks, storage);
        String output = parser.parseInput("todo Join Club");
        assertEquals(expected, output);
    }

    /** Runs a test to create a Deadline Task by parsing user input */
    @Test
    public void parserDeadlineTest_createDeadlineTask_success() throws DukeException, IOException {
        String expected = "Added Deadline: D - 0 - Assignment - 2022/May/14";
        Parser parser = new Parser( tasks, storage);
        String output = parser.parseInput("deadline Assignment /by 2022/May/14");
        assertEquals(expected, output);
    }

    /** Runs a test to create an Event Task by parsing user input */
    @Test
    public void parserEventTest_createEventTask_success() throws DukeException, IOException {
        String expected = "Added Event: E - 0 - Project Meeting - 2022/January/30";
        Parser parser = new Parser( tasks, storage);
        String output = parser.parseInput("event Project Meeting /at 2022/January/30");
        assertEquals(expected, output);
    }
}
