package task;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("testing"));
        taskList.addTask(new Deadline("homework", "2022-02-11T11:59:00"));

        assertEquals(taskList.size(), 2);
        assertEquals(taskList.getTask(0).toString(),"[T][ ] testing");

        taskList.removeTask(0);

        assertEquals(taskList.size(), 1);
        assertEquals(taskList.getTask(0).toString(), "[D][ ] homework (by: Feb 11 2022 11:59:00)");
    }
}
