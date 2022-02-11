package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void TimeParser_WithoutDisk_expectedBehavior() {
        Event freshEvent = new Event("do this", "2020-10-10 0000", false);
        assertEquals("[E][ ] do this (at: Oct 10 2020 1200AM)", freshEvent.toString());
    }

    @Test
    public void TimeParser_WithDisk_expectedBehavior() {
        Event freshEvent = new Event("do this", "Oct 10 2020 1200AM", true);
        assertEquals("[E][ ] do this (at: Oct 10 2020 1200AM)", freshEvent.toString());
    }
}
