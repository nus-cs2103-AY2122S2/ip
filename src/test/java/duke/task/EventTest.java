package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents an event test
 */
public class EventTest {

    /**
     * Tests if event toString() method gives the correct output
     */
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] project meeting (at: Aug 6th 2-4pm)",
                new Event("project meeting", "Aug 6th 2-4pm").toString());
    }
}
