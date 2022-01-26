package duke.junit;

import duke.exception.DukeException;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() throws DukeException {
        Event event1 = new Event("test 1", "2022-01-01 10:00");
        assertEquals("[E][ ] test 1 (at: Jan 1 2022 10:00 am)", event1.toString(), "pass");
    }

    @Test
    public void testToString2() throws DukeException {
        Event event2 = new Event("test 1", "2022-01-01 10:00");
        assertEquals("[E][ ] test 1 (at: 2022-01-01 10:00)", event2.getTaskData(), "pass");
    }
}
