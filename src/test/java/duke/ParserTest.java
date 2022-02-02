package duke;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    Ui ui = new Ui();
    TaskList tasks = new TaskList();
    Storage storage = new Storage("data/duke.txt", tasks);

    @Test
    public void parserToDoTest_createToDoTask_success() throws DukeException, IOException {
        String expected = "Added Todo: T - 0 - Join Club";
        Parser parser = new Parser(tasks, storage);
        String output = parser.parseInput("todo Join Club");
        assertEquals(expected, output);
    }

    @Test
    public void parserDeadlineTest_createDeadlineTask_success() throws DukeException, IOException {
        String expected = "Added Deadline: D - 0 - Assignment - 2022/May/14";
        Parser parser = new Parser(tasks, storage);
        String output = parser.parseInput("deadline Assignment /by 2022/May/14");
        assertEquals(expected, output);
    }

    @Test
    public void parserEventTest_createEventTask_success() throws DukeException, IOException {
        String expected = "Added Event: E - 0 - Project Meeting - 2022/January/30";
        Parser parser = new Parser(tasks, storage);
        String output = parser.parseInput("event Project Meeting /at 2022/January/30");
        assertEquals(expected, output);
    }
}
