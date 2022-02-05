package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;


public class TaskListTest {
    @Test
    public void taskListTest1() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void taskListTest2() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("task1", false, "D", "2011-11-11 12:30");
        taskList.add(deadline);
        assertEquals(1, taskList.size());
    }

    @Test
    public void taskListTest3() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("task1", false, "D", "2011-11-11 12:30");
        taskList.add(deadline);
        taskList.delete("delete 1");
        assertEquals(0, taskList.size());
    }
}
