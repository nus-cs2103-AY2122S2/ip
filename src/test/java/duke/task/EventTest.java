package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EventTest uses an assert, to verify correct values of Event creation.
 */
public class EventTest {
    @Test
    public void testEvent() {
        assertEquals("[E][ ] watch concert (at: July 7th 8-11pm)",
                new Event("watch concert", "July 7th 8-11pm").toString());
    }

}
