package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.ui.Ui;


/**
 * Tests whether TaskList functions work as intended.
 */
public class TaskListTest {
    private static TaskList taskList = new TaskList();
    private static TaskList initialList = new TaskList();
    private static Ui testUi = new Ui();

    /**
     * Populates taskList and initialList with the same content.
     */
    @BeforeEach
    public void setUpTaskList() {
        taskList.getTasks().clear();
        taskList.getTasks().add(new ToDo("Hello", testUi));
        taskList.getTasks().add(new ToDo("World", testUi));

        initialList.getTasks().clear();
        initialList.getTasks().add(new ToDo("Hello", testUi));
        initialList.getTasks().add(new ToDo("World", testUi));
    }

    /**
     * Tests whether addTask adds a task correctly
     */
    @Test
    public void addTaskTest() {
        taskList.addTask(new ToDo("content", testUi));
        initialList.getTasks().add(new ToDo("content", testUi));
        Assertions.assertEquals(taskList, initialList);
    }

    /**
     * Tests whether deleteTask deletes a task correctly
     */
    @Test
    public void deleteTaskTest() throws DukeInvalidArgumentException {
        taskList.getTasks().add(new ToDo("what", testUi));
        taskList.deleteTask(3);
        Assertions.assertEquals(taskList, initialList);
        String expectedMessage = "index is not in the list";
        DukeException exception = Assertions.assertThrows(DukeException.class, () -> {
            taskList.deleteTask(6);
        });
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
