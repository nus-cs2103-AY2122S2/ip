package bernie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;


public class TaskListTest {
    @Test
    public void getSizeTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new String[]{"borrow book"}, Type.TODO);
        tasks.addTask(new String[]{"return book", "2022-01-28"}, Type.DEADLINE);
        tasks.addTask(new String[]{"project meeting", "Mon 2-4pm"}, Type.EVENT);
        assertEquals(3, tasks.getSize());
    }

    @Test
    public void numTasksLeftTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new String[]{"borrow book"}, Type.TODO);
        tasks.addTask(new String[]{"return book", "2022-01-28"}, Type.DEADLINE);
        tasks.addTask(new String[]{"project meeting", "Mon 2-4pm"}, Type.EVENT);
        tasks.markTask(Type.MARK, "3");
        assertEquals(2, tasks.numTasksLeft());
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new String[]{"borrow book"}, Type.TODO);
        tasks.addTask(new String[]{"return book", "2022-01-28"}, Type.DEADLINE);
        tasks.addTask(new String[]{"project meeting", "Mon 2-4pm"}, Type.EVENT);
        Task deletedTask = new Event("project meeting", "Mon 2-4pm");
        assertEquals(deletedTask.toString(), tasks.deleteTask("3").toString());
    }

    @Test
    public void isEmptyTest() {
        assertEquals(true, new TaskList().isEmpty());
    }

    @Test
    public void getTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new String[]{"borrow book"}, Type.TODO);
        Task createdTask = new ToDo("borrow book");
        assertEquals(createdTask.toString(), tasks.getTask(0).toString());
    }

    @Test
    public void taskExistsTest() {
        TaskList tasks = new TaskList();
        try {
            tasks.checkTaskExists("1");
        } catch (InvalidArgumentException e) {
            assertEquals("Task number does not exist!", e.getMessage());
        }
    }
}
