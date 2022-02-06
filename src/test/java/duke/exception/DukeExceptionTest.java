package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Tasklist;

/**
 * Returns an object used to test the functionality of the referenced class.
 */
public class DukeExceptionTest {
    @Test
    public void taskValidation_invalidInput_exceptionThrown() {
        try {
            DukeException.isTaskValid(8, "event t /at -12/12/1999", "event", new Tasklist());
        } catch (DukeException err) {
            assertEquals("Please provide time in the format 'DD/MM/YYYY <time>'.\n", err.getMessage());
        }
    }

    @Test
    public void indexValidation_invalidDigit_exceptionThrown() {
        try {
            DukeException.isIndexValid("mark one", new Tasklist());
        } catch (DukeException err) {
            assertEquals("Please key in a valid digit.\n", err.getMessage());
        }
    }
}
