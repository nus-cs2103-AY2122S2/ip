package duke;

import duke.task.tasks.Deadline;
import duke.task.tasks.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("eat", "2019-12-12");
        assertEquals(taskList.numTasks(), 0);

        for (int i = 0; i < 100; i++) {
            taskList.addTask(deadline);
        }

        assertEquals(taskList.numTasks(), 100);

    }

    @Test
    public void removeTaskTest() {
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("eat", "2019-12-12");

        for (int i = 0; i < 100; i++) {
            taskList.addTask(deadline);
        }

        assertEquals(taskList.numTasks(), 100);

        for (int i = 0; i < 50; i++) {
            try {
                taskList.remove(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        assertEquals(taskList.numTasks(), 50);


    }
}
