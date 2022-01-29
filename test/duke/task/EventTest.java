package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class EventTest {

    @Test
    void format_incorrectDateFormat_exceptionThrown() {
        try {
            Event event = new Event("new event task", "1st Jan 2021", false);
            fail();
        } catch (DukeException e) {
            assertEquals("Date was incorrectly formatted! Please format it as yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    void format_correctDateFormat_success() {
        try {
            Event event = new Event("new event task", "2021-01-01", false);
            assertEquals("[E][ ] new event task (at: Jan 1 2021)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}