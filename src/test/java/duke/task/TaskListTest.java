package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    @Test
    public void populateWith_legalData() {
        String[] data = new String[] {"T,task1,, ", "E,task2,8pm,X", "D,task3,2022-01-31, "};
        TaskList taskList = new TaskList();
        try {
            taskList.populateWith(data);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void populateWith_illegalData_exceptionThrown() {
        String[] data = new String[] {"T,task1,, ", "E,task2,8pm,X", "D,task3,helloError, "};
        TaskList taskList = new TaskList();
        try {
            taskList.populateWith(data);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Encounter incorrect file format when loading tasks from storage");
        }
    }

    @Test
    public void getNumberOfTasks_emptyList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getNumberOfTasks());
    }

    @Test
    public void getNumberOfTasks_nonEmptyList() {
        String[] data = new String[] {"T,task1,, ", "E,task2,8pm,X", "D,task3,2022-01-31, "};
        TaskList taskList = new TaskList();
        try {
            taskList.populateWith(data);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(3, taskList.getNumberOfTasks());
    }

    @Test
    public void isValidTaskIndex_emptyList() {
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.isValidTaskIndex(0));
        assertEquals(false, taskList.isValidTaskIndex(-1));
        assertEquals(false, taskList.isValidTaskIndex(1));
    }

    @Test
    public void isValidTaskIndex_nonEmptyList() {
        String[] data = new String[] {"T,task1,, ", "E,task2,8pm,X", "D,task3,2022-01-31, "};
        TaskList taskList = new TaskList();
        try {
            taskList.populateWith(data);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(false, taskList.isValidTaskIndex(-1));
        assertEquals(false, taskList.isValidTaskIndex(4));
        assertEquals(false, taskList.isValidTaskIndex(3));
        assertEquals(true, taskList.isValidTaskIndex(0));
        assertEquals(true, taskList.isValidTaskIndex(1));
        assertEquals(true, taskList.isValidTaskIndex(2));
    }
}
