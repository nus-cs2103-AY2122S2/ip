import johnny.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getTaskString_todoTask_success() {
        assertEquals("[ ] Attend tutorial", new Task("Attend tutorial", false).toString());
    }
}
