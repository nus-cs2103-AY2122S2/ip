package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
    public void statusIconTest() {
        Task task = new Task("Read book");
        task.complete();
        assertEquals(task.getStatusIcon(), "X");
    }

    @Test
    public void completeTest() {
        Task task = new Task("Read book");
        task.complete();
        assertTrue(task.isDone);
    }

    @Test
    public void toStringTest() {
        Task task = new Task("Read book");
        assertEquals(task.toString(), "[ ] Read book");
    }
}
