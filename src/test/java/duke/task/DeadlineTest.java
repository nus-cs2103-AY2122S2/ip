package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a deadline test
 */
public class DeadlineTest {

    /**
     * Tests if deadline toString() method gives the correct output
     */
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: June 6th)",
                new Deadline("return book", "June 6th").toString());
    }
}
