package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListTest1() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void TaskListTest2() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("task1", false, "D", "2011-11-11 12:30");
        taskList.add(deadline);
        assertEquals(1, taskList.size());
    }

    @Test
    public void TaskListTest3() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("task1", false, "D", "2011-11-11 12:30");
        taskList.add(deadline);
        taskList.delete("delete 1");
        assertEquals(0, taskList.size());
    }
}
