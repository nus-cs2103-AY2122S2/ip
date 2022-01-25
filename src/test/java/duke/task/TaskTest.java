package duke.task;

import duke.exception.InvalidActionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] this week iP", new Todo("this week iP").toString());
    }

    @Test
    public void markAsDone_completedTask_exceptionThrown() {
        try {
            Todo t = new Todo("this week iP",true);
            t.markAsDone();
            assertEquals("[T][X] this week iP", t.toString());
            fail();
        } catch (InvalidActionException e) {
            assertEquals("Task already done!", e.getMessage());
        }
    }

    @Test
    public void markUndone_completedTask_success() {
        try {
            Todo t = new Todo("this week iP",true);
            t.markUndone();
            assertEquals("[T][ ] this week iP", t.toString());
        } catch (InvalidActionException e) {
            fail();
        }
    }
}
