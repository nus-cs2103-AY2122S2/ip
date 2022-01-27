package bernie.tasks;

import bernie.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEventTest() {
        assertEquals("[E][ ] project meeting (at: Mon 2-4pm)", new Event("project meeting", "Mon 2-4pm").toString());
    }

    @Test
    public void markMarkedEventTest() {
        Task event = new Event("project meeting", "Mon 2-4pm");
        event.markDone();
        try {
            event.checkMark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot mark a task already done!", e.getMessage());
        }
    }

    @Test
    public void unmarkUnmarkedEventTest() {
        Task event = new Event("project meeting", "Mon 2-4pm");
        event.markNotDone();
        try {
            event.checkUnmark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot unmark a task not done!", e.getMessage());
        }
    }

    @Test
    public void markEventTest() {
        Task event = new Event("Dinner date", "Mon 7pm");
        event.markDone();
        assertEquals("[E][X] Dinner date (at: Mon 7pm)", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Task event = new Event("Dinner date", "Mon 7pm");
        event.markDone();
        event.markNotDone();
        assertEquals("[E][ ] Dinner date (at: Mon 7pm)", event.toString());
    }
}
