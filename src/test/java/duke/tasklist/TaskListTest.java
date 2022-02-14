package duke.tasklist;

import duke.task.tasks.Deadline;
import duke.task.tasks.ITask;
import duke.task.tasks.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    static TaskList taskList;

    @BeforeAll
    static void initializeTest() {
        ArrayList<ITask> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new TaskStub());
        }
        taskList = new TaskList(tasks);
    }

    @Test
    void addTask_validInput_success() {
        int numberOfTask = taskList.getNumberOfTasks();
        taskList.addTask(new TaskStub());
        assertEquals(numberOfTask + 1, taskList.getNumberOfTasks());
    }

    @Test
    void addTask_invalidInput_throwAssertionError() {
        try {
            taskList.addTask(null);
            fail("Should throw an error");
        } catch (Error e) {
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    void mark_validIndex_success() {
        try {
            taskList.mark(1, "mark");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void mark_invalidIndex_throwException() {
        try {
            taskList.mark(0, "mark");
            fail();
        } catch (Exception e) {
            assertEquals("Oops! your input number is invalid!", e.getMessage());
        }
    }

    @Test
    void remove_validIndex_success() {
        try {
            taskList.remove(1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void remove_invalidIndex_success() {
        try {
            taskList.remove(0);
            fail();
        } catch (Exception e) {
            assertEquals("Oops! your input number is invalid!", e.getMessage());
        }
    }
}
