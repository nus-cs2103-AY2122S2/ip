import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import luca.task.Task;
import luca.task.TaskList;
import luca.task.ToDo;

/**
 * Tests the TaskList class.
 */
public class TaskListTest {

    /**
     * Size description varying for 1 task.
     */
    @Test
    public void sizeDescription_oneTask_success() {
        Task task = new ToDo("Test");
        TaskList taskList = TaskList.of(task);
        assertEquals(taskList.sizeDescription(), "Now you have 1 task in the list.");
    }

    /**
     * Size description varying with 2 task.
     */
    @Test
    public void sizeDescription_twoTask_success() {
        Task task1 = new ToDo("Test1");
        Task task2 = new ToDo("Test2");
        TaskList taskList = TaskList.of(task1, task2);
        assertEquals(taskList.sizeDescription(), "Now you have 2 tasks in the list.");
    }
}
