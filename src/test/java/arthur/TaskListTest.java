package arthur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void todo_checkOutput_success() {
        assertEquals("Added a new Task.Todo task: \n"
                + "[T][ ] >> test\n"
                + "You have 1 tasks in list at the moment.",
                new TaskList(new Storage(new Ui())).todo("test"));

        assertEquals("Added a new Task.Todo task: \n"
                        + "[T][ ] >> testing\n"
                        + "You have 1 tasks in list at the moment.",
                new TaskList(new Storage(new Ui())).todo("testing"));
    }

    @Test
    public void deadLine_checkOutput_success() {
        assertEquals("Added a new Task.Deadline task: \n"
                        + "[D][ ] >> testing (By: 15 Apr 2021)\n"
                        + "You have 1 tasks in list at the moment.",
                new TaskList(new Storage(new Ui())).deadline("testing /by 2021-04-15"));
    }

    @Test
    public void deadLine_checkExceptionHandling_exceptionThrow() {
        assertEquals("Please add the deadline date",
                new TaskList(new Storage(new Ui())).deadline("testing /by "));
    }
}
