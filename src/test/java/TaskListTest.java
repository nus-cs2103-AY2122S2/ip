import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Tests the TaskList class.
 */
public class TaskListTest {

    /**
     * Size description varying for 1 task.
     */
    @Test
    public void sizeDescription_oneTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Test"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(taskList.sizeDescription(), "Now you have 1 task in the list.");
    }

    /**
     * Size description varying with 2 task.
     */
    @Test
    public void sizeDescription_twoTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Test2"));
        tasks.add(new ToDo("Test1"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(taskList.sizeDescription(), "Now you have 2 tasks in the list.");
    }
}


