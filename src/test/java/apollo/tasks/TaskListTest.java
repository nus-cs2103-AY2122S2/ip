package apollo.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private TaskList taskList;
    private Task todo;
    private Task deadline;
    private Task event;

    @BeforeEach
    void setUp() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 20, 0, 0);
        taskList = new TaskList();
        todo = new Todo("todo task");
        deadline = new Deadline("deadline task", dateTime);
        event = new Event("event task", dateTime);
    }

    @AfterEach
    void tearDown() {
        taskList = null;
        todo = null;
        deadline = null;
        event = null;
    }

    @Test @Order(1)
    void addTask_addThreeTask_threeTaskCount() {
        int empty = taskList.taskCount();
        assertEquals(empty, 0);

        taskList.addTask(todo);
        int one = taskList.taskCount();
        assertEquals(one, 1);

        taskList.addTask(deadline);
        int two = taskList.taskCount();
        assertEquals(two, 2);

        taskList.addTask(event);
        int three = taskList.taskCount();
        assertEquals(three, 3);
    }

    @Test @Order(2)
    void deleteTask_deleteThreeTask_taskInstanceReturned() {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        int three = taskList.taskCount();
        assertEquals(three, 3);

        Task deadline = taskList.deleteTask(1);
        int two = taskList.taskCount();
        assertEquals(two, 2);
        assertTrue(deadline instanceof Deadline);

        Task todo = taskList.deleteTask(0);
        int one = taskList.taskCount();
        assertEquals(one, 1);
        assertTrue(todo instanceof Todo);

        Task event = taskList.deleteTask(0);
        int empty = taskList.taskCount();
        assertEquals(empty, 0);
        assertTrue(event instanceof Event);
    }

    @Test @Order(4)
    void markTask_markUnmarkEvent() {
        taskList.addTask(event);

        taskList.markTask(0, true);
        String markEventExpected = "[E][X] event task (at: 20-01-2022 00:00)";
        assertEquals(markEventExpected, taskList.getTaskString(0));

        taskList.markTask(0, false);
        String unmarkEventExpected = "[E][ ] event task (at: 20-01-2022 00:00)";
        assertEquals(unmarkEventExpected, taskList.getTaskString(0));
    }

    @Test @Order(3)
    void getTaskString_todoDeadlineEvent() {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        String todoExpected = "[T][ ] todo task";
        String deadlineExpected = "[D][ ] deadline task (by: 20-01-2022 00:00)";
        String eventExpected = "[E][ ] event task (at: 20-01-2022 00:00)";

        assertEquals(todoExpected, taskList.getTaskString(0));
        assertEquals(deadlineExpected, taskList.getTaskString(1));
        assertEquals(eventExpected, taskList.getTaskString(2));
    }

    @Test @Order(5)
    void getTaskDescription_todoDeadlineEvent() {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        String todoExpected = "todo task";
        String deadlineExpected = "deadline task";
        String eventExpected = "event task";

        assertEquals(todoExpected, taskList.getTaskDescription(0));
        assertEquals(deadlineExpected, taskList.getTaskDescription(1));
        assertEquals(eventExpected, taskList.getTaskDescription(2));
    }
}
