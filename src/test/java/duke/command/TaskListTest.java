package duke.command;

import duke.DukeException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.listItem(), "No items in the list");
    }

    @Test
    public void taskListTest1() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> Parser.parseInput("deadline /by test", taskList));
    }
}
