import Duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    /**
     * Testing method
     */
    @Test
    public void getDescription_and_getStatusIcon_Test1() {
        Task t = new Task("testing");
        assertEquals("testing", t.getDescription());
        assertEquals(" ", t.getStatusIcon());
    }

    /**
     * Testing method
     */
    @Test
    public void getDescription_and_getStatusIcon_Test2() {
        Task t = new Task("testing123");
        t.markAsDone();
        assertEquals("testing123", t.getDescription());
        assertEquals("X", t.getStatusIcon());
    }


}
