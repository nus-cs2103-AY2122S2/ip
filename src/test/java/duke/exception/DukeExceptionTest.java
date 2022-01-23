package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Tasklist;

public class DukeExceptionTest {
    @Test
    public void taskValidation_invalidInput_exceptionThrown() {
        try {
            DukeException.taskValidity(8, "event t /at -12/12/1999", "event");
        } catch (DukeException err) {
            assertEquals("Please provide time in the format 'DD/MM/YYYY <time>'.\n", err.getMessage());
        }
    }

    @Test
    public void indexValidation_invalidDigit_exceptionThrown() {
        try {
            DukeException.indexValidity("mark one", new Tasklist());
        } catch (DukeException err) {
            assertEquals("Please key in a valid digit.\n", err.getMessage());
        }
    }
}
