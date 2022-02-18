import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {


    @Test
    public void testName() {
        assertEquals("Return book", new Task("Return book").getName());
    }

    @Test
    public void testDone() {
        Task sub3 = new Task("Return book");
        sub3.markAsDone();
        assertTrue(sub3.isDone());
    }



    @Test
    public void testStatus() {
        assertEquals("[ ]Return book", new Task("Return book").toString());
        Task sub6 = new Task("Return book");
        sub6.markAsDone();
        assertEquals("[X]Return book", sub6.toString());
    }
}