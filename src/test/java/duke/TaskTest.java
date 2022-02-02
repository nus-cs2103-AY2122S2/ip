package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void getStatusIconTest() {
        assertEquals(" ", new Task("test description").getStatusIcon());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] test description", new Task("test description").toString());
    }
}
