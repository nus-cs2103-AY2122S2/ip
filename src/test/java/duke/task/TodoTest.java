package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a todo test
 */
public class TodoTest {

    /**
     * Tests if todo toString() method gives the correct output
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read book",
                new Todo("read book").toString());
    }
}
