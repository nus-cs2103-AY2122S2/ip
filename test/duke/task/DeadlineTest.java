package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void format_incorrectDateFormat_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("new deadline task", "1st Jan 2021", false);
            fail();
        } catch (DukeException e) {
            assertEquals("Date was incorrectly formatted! Please format it as yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    void format_correctDateFormat_success() {
        try {
            Deadline deadline = new Deadline("new deadline task", "2021-01-01", false);
            assertEquals("[D][ ] new deadline task (by: Jan 1 2021)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}