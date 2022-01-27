import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDescription() {
        String text = "Trick";
        Task testTask = new Task(text);
        assertEquals(text, testTask.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        Task testTask = new Task("stuff", true);
        assertEquals("X", testTask.getStatusIcon());

        testTask = new Task("stuff", false);
        assertEquals(" ", testTask.getStatusIcon());
    }

    @Test
    public void testToString() {
        Task testTask = new Task("stuff", true);
        assertEquals("[X] stuff", testTask.toString());
    }
}
