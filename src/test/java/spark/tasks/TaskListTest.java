package spark.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.exceptions.SparkException;
import spark.exceptions.taskmodificationexceptions.TaskNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("M-d-yyyy Hmm");
    private static LocalDateTime validDateTime;

    @BeforeAll
    public static void instantiateValidDateTime() {
        String dateTimeString = "2-22-2022 1800";
        validDateTime = LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    @Test
    public void testAddValidToDo() {
        // a unit test for TaskList#addToDo method
        TaskList taskList = new TaskList();

        try {
            taskList.addTodo("Buy milk");
        } catch (SparkException e) {
            fail(e.getMessage());
        }

        assertEquals(true, !taskList.findTask("Buy milk").isEmpty());
    }

    @Test
    public void testAddValidDeadline() {
        // a unit test for TaskList#addDeadline method
        TaskList taskList = new TaskList();

        taskList.addDeadline("Do homework", validDateTime);

        assertEquals(true, !taskList.findTask("Do homework").isEmpty());
    }

    @Test
    public void testMarkValidTask() {
        // a unit test for TaskList#mark method
        TaskList taskList = new TaskList();

        try {
            taskList.addTodo("buy milk");
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
            taskList.addTodo("buy milk");
        } catch (SparkException e) {
            fail(e.getMessage());
        }

        // mark a Task that doesn't exist yet
        assertThrows(TaskNotFoundException.class, () -> taskList.markTask(5));
    }
}
