package duke;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void sizeTest() throws DukeException {
        TaskList tasklist = new TaskList();
        tasklist.addTask(new Todo("borrow book"));
        tasklist.addTask(new Deadline("borrow phone", "2012-03-04 0755"));
        tasklist.addTask(new Event("project meeting", "2012-03-04 0600", "2012-03-04 0700"));
        assertEquals(3,tasklist.getSize());
    }

    @Test
    public void markTest() {
        TaskList tasklist = new TaskList();
        tasklist.addTask(new Todo("0"));
        tasklist.getList().get(0).setDone();
        assertEquals("[T][X] 0", tasklist.getList().get(0).toString().strip());
    }
}
