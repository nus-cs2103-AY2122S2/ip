package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void sizeTest() {
        TaskList tasklist = new TaskList();
        tasklist.addTask(new Todo("borrow book"));
        tasklist.addTask(new Deadline("borrow knife", "2012/03/04 0755"));
        tasklist.addTask(new Event("project meeting", "6PM"));
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
