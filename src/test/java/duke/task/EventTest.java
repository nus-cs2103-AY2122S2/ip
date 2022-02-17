package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEvent() {
        assertEquals("[E][ ] watch concert (at: July 7th 8-11pm)",
                new Event("watch concert", "July 7th 8-11pm").toString());
    }

}
