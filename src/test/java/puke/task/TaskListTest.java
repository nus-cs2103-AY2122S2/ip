package puke.task;

import puke.exception.PukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList tasks = new TaskList();

    @Test
    public void addTask_newDeadline_success() {
        try {
            assertEquals("Got it. I've added this task:\n  [D][ ] cs2103 tasks (by: 2023-01-28 12:00)\n" +
                            "Now you have 1 task in the list.",
                    tasks.addTask("deadline", "cs2103 tasks /by 2023-01-28 12:00"));
        } catch (PukeException e) {
            fail();
        }
    }

    @Test
    public void addTask_invalidDateTime_exceptionThrown() {
        try {
            assertEquals(0, tasks.addTask("deadline", "cs2103 tasks /by tmr morning"));
        } catch (PukeException e) {
            assertEquals("I'll need a valid date/time in the format yyyy-mm-dd hh:mm", e.getMessage());
        }
    }
}
