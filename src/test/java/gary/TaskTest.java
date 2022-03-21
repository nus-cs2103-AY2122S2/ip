package gary;
import gary.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the correctness of gary.Task
 */
public class TaskTest {

    /**
     * Test for the correctness of gary.Task.mark() method
     */
    @Test
    public void markTest() {
        Task t = new Task("testing");
        t.toMark();
        assertEquals("[X] testing", t.toString());
    }

    /**
     * Test for the correctness of gary.Task.unmark() method
     */
    @Test
    public void unmarkTest() {
        Task t = new Task("un-mark");
        t.toMark();
        t.toUnmark();
        assertEquals("[ ] un-mark", t.toString());
    }
}