package chatcat.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createTask() {
        assertEquals("[ ] test", new Task("test").toString());
    }

    @Test
    public void done() {
        Task task = new Task("test");
        task.setDone();
        assertEquals("[X] test", task.toString());
    }

    @Test
    public void undone() {
        Task task = new Task("test");
        task.setDone();
        task.setUnDone();
        assertEquals("[ ] test", task.toString());
    }
}
