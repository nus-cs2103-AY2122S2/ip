import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ExceptionHandler;
import duke.Storage;
import duke.TaskList;
import duke.ToDos;
import duke.Ui;

/**
 * Test class for testing TaskList class.
 */
public class TaskListTest {
    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    /**
    * Tests addTaskTest method of TaskList class.
    */
    @Test
    public void addTaskTest() {
        listOfTasks = new TaskList();
        listOfTasks.addTask(new ToDos("todo buy bread"));
        assertEquals("todo buy bread", listOfTasks.getListOfTasks().get(0).getDescription());
        assertEquals(1, listOfTasks.getSize());
    }

    /**
     * Tests removeTask method of TaskList class.
     */
    @Test
    public void removeTaskTest() throws ExceptionHandler {
        listOfTasks = new TaskList();
        listOfTasks.addTask(new ToDos("todo buy bread"));
        listOfTasks.addTask(new ToDos("todo buy butter"));
        listOfTasks.removeTask(1);
        assertEquals(1, listOfTasks.getSize());
        assertEquals("todo buy bread", listOfTasks.getListOfTasks().get(0).getDescription());
    }
}
