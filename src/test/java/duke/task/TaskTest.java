package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void dummyTask() {
        Task task = new Task("Test Task", 'T');

        assertEquals("Test Task", task.getDescription());
        assertEquals('T', task.getType());
        assertFalse(task.getCompleted());
    }

    @Test
    public void testUnmarkedStatusIcon() {
        Task task = new Task("Test Task", 'T');

        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testMarkedStatusIcon() {
        Task task = new Task("Test Task", 'T');

        task.setCompleted(true);

        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testToggleCompleted() {
        Task task = new Task("Test Task", 'T');

        assertFalse(task.getCompleted(), "Test Task is not done.");

        task.setCompleted(true);

        assertTrue(task.getCompleted(), "Test Task is done.");
    }

    @Test
    public void testToString() {
        Task task = new Task("Test Task", 'T');

        assertEquals("[ ] Test Task", task.toString());
    }
}
