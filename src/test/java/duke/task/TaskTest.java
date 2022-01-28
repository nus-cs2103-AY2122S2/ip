package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }

    @Test
    public void testGetters() {
        Task temp = new Task("read book");
        assertEquals("read book", temp.getDescription());
        assertEquals(" ", temp.getStatusIcon());
    }

    @Test
    public void testSetters() {
        Task temp = new Task("run");
        temp.setTaskDone();
        assertEquals("X", temp.getStatusIcon());
        temp.setTaskNotDone();
        assertEquals(" ", temp.getStatusIcon());
    }
}