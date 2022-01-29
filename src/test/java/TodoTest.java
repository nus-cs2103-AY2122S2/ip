import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void getDetailTest() {
        Task testTask = new Todo("Clean my room");
        testTask.markDone();
        assertEquals("T | 1 | Clean my room\n", testTask.getDetail());
    }


}
