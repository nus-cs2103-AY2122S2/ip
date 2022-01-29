package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TaskTest {
    @Test
    public void taskToStringTest() {
        assertEquals("[ ] run 10km", new Task("run 10km").toString());
    }
    @Test
    public void taskMarkAsDoneTest() {
        Task testTask = new Task("run 10km");
        testTask.markAsDone();
        assertTrue(testTask.isDone);
    }
    @Test
    public void taskUnmarkTest() {
        Task testTask = new Task("run 10km");
        testTask.markAsDone();
        testTask.unmark();
        assertFalse(testTask.isDone);
    }

    @Test
    public void taskGetStatusIconTest() {
        assertEquals(" ", new Task("run 10km").getStatusIcon());
    }

    @Test
    public void taskIsDoneTest() {
        Task testTask = new Task("run 10km");
        assertEquals('0', testTask.isDone());
        testTask.markAsDone();
        assertEquals('1', testTask.isDone());
    }
}
