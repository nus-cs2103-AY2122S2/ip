package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {
    Ui ui = new Ui();
    TaskList tasks = new TaskList();
    Storage storage = new Storage("data/duke.txt", tasks);
    @Test
    public void parserToDoTest_createToDoTask_success() throws DukeException {
        String expected = "Added Todo: T - 0 - Join Club";
        Parser parser = new Parser("todo Join Club", tasks, storage);
        String output = parser.createToDoTask();
        assertEquals(expected, output);
    }

    @Test
    public void parserDeadlineTest_createDeadlineTask_success() throws DukeException {
        String expected = "Added Deadline: D - 0 - Assignment - 2022/May/14";
        Parser parser = new Parser("deadline Assignment /by 2022/May/14", tasks, storage);
        String output = parser.createDeadlineTask();
        assertEquals(expected, output);
    }

    @Test
    public void parserEventTest_createEventTask_success() throws DukeException {
        String expected = "Added Event: E - 0 - Project Meeting - 2022/January/30";
        Parser parser = new Parser("event Project Meeting /at 2022/January/30", tasks, storage);
        String output = parser.createEventTask();
        assertEquals(expected, output);
    }
}
