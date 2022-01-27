package spark.tasks;

import org.junit.jupiter.api.Test;
import spark.exceptions.SparkException;
import spark.exceptions.formatexceptions.InvalidDeadlineParamsException;
import spark.exceptions.formatexceptions.InvalidToDoParamsException;
import spark.exceptions.taskmodificationexceptions.TaskNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testAddValidToDo() {
        // a unit test for TaskList#addToDo method
        TaskList taskList = new TaskList();

        try {
            taskList.addToDo("Buy milk");
        } catch (SparkException e) {
            fail(e.getMessage());
        }

        assertEquals(true, !taskList.findTask("Buy milk").isEmpty());
    }

    @Test
    public void testAddValidDeadline() {
        // a unit test for TaskList#addDeadline method
        TaskList taskList = new TaskList();

        taskList.addDeadline("Do homework", "2-22-2022 1800");

        assertEquals(true, !taskList.findTask("Do homework").isEmpty());
    }

    @Test
    public void testMarkValidTask() {
        // a unit test for TaskList#mark method
        TaskList taskList = new TaskList();

        try {
            taskList.addToDo("buy milk");
            taskList.markTask(1);
            assertTrue(taskList.getLastAddedTask().isDone());
        } catch (SparkException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void markTask_markNonExistentTask_throwsException() {
        // a unit test for TaskList#mark method
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("buy milk");
        } catch (SparkException e) {
            fail(e.getMessage());
        }

        // mark a Task that doesn't exist yet
        assertThrows(TaskNotFoundException.class, () -> taskList.markTask(5));
    }
}
