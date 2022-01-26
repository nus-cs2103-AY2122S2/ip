package duke.junit;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testListSize() throws DukeException {
        TaskList event1 = new TaskList();
        assertEquals(0, event1.getSize(), "pass");
    }

    @Test
    public void testListSize2() throws DukeException {
        TaskList event2 = new TaskList();
        event2.addTask(new Task("hi"));
        assertEquals(1, event2.getSize(), "pass");
    }
}
