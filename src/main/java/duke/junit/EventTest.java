package duke.junit;
import duke.exception.DukeException;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test event class for JUnit testing
 */
public class EventTest {
    /**
     * test if string of task added is correct
     *
     * @throws DukeException when task cannot be added
     */
    @Test
    public void testToString() throws DukeException {
        Event event1 = new Event("test 1", "2022-01-01 10:00");
        assertEquals("[E][ ] test 1 (at: Jan 1 2022 10:00 am)", event1.toString(), "pass");
    }

    /**
     * test if string of task data stored is correct
     *
     * @throws DukeException when task data cannot be added
     */
    @Test
    public void testToString2() throws DukeException {
        Event event2 = new Event("test 1", "2022-01-01 10:00");
        assertEquals("[E][ ] test 1 (at: 2022-01-01 10:00)", event2.getTaskData(), "pass");
    }
}
