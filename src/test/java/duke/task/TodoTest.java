package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] buy lunch", new Todo("buy lunch").toString());
    }

    @Test
    public void getType() {
        assertEquals("T", new Todo("buy lunch").getType());
    }
}
