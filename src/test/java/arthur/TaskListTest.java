package arthur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import arthur.exceptions.InvalidStoredDataFormat;

public class TaskListTest {
    @Test
    public void todo_checkOutput_success() {
        try {
            assertEquals("Added a new Task. Todo task: \n"
                    + "[T][ ] >> test\n"
                    + "You have 1 tasks in list at the moment.",
                    new TaskList(new Storage()).todo("test"));
        } catch (InvalidStoredDataFormat e) {
            fail();
        }

        try {
            assertEquals("Added a new Task. Todo task: \n"
                            + "[T][ ] >> testing\n"
                            + "You have 1 tasks in list at the moment.",
                    new TaskList(new Storage()).todo("testing"));
        } catch (InvalidStoredDataFormat e) {
            fail();
        }
    }

    @Test
    public void deadLine_checkOutput_success() {
        try {
            assertEquals("Added a new Task. Deadline task: \n"
                            + "[D][ ] >> testing (By: 15 Apr 2021)\n"
                            + "You have 1 tasks in list at the moment.",
                    new TaskList(new Storage()).deadline("testing /by 2021-04-15"));
        } catch (InvalidStoredDataFormat e) {
            fail();
        }
    }

    @Test
    public void deadLine_checkExceptionHandling_exceptionThrow() {
        try {
            assertEquals("Please add the deadline date",
                    new TaskList(new Storage()).deadline("testing /by "));
        } catch (InvalidStoredDataFormat e) {
            fail();
        }
    }
}
