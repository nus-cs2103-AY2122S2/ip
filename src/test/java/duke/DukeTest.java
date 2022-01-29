package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class DukeTest {

    @Test
    public void testTodoToString() {
        ToDo td = new ToDo("something");
        assertEquals("[T][ ] something", td.toString());
    }

    @Test
    public void testTodoFormatSave() {
        ToDo td = new ToDo("something");
        assertEquals("T |0| something", td.formatSave());
    }

    @Test
    public void testDeadlineConstructor_correctFormat_noException() {
        try {
            Deadline dl = new Deadline("something1", "2022-01-03 1800");
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testDeadlineConstructor_wrongDateFormat_exceptionThrown() {
        try {
            Deadline dl = new Deadline("something2", "2022-01/04 1800");
            Assertions.fail("input should be wrong");
        } catch (DukeException e) {
            assertTrue(e instanceof DukeWrongInputFormatException);
            assertEquals("Format for deadline is wrong. Please refer to list of commands.", e.getMessage());
        }
    }

    @Test
    public void testDeadlineToString_correctFormat_noException() {
        try {
            Deadline dl = new Deadline("something1", "2022-01-03 1800");
            assertEquals("[D][ ] something1 (by: Jan 03 2022 18:00)", dl.toString());
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testDeadlineFormatSave_correctFormat_noException() {
        try {
            Deadline dl = new Deadline("something3", "2022-01-23 1547");
            assertEquals("D |0| something3 /by 2022-01-23 1547", dl.formatSave());
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testEventConstructor_correctFormat_noException() {
        try {
            Event eve = new Event("something5", "2022-01-03 1830");
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testEventConstructor_wrongDateFormat_exceptionThrown() {
        try {
            Event eve = new Event("something90", "2022-01-04 18:00");
            Assertions.fail("input should be wrong");
        } catch (DukeException e) {
            assertTrue(e instanceof DukeWrongInputFormatException);
            assertEquals("Format for event is wrong. Please refer to list of commands.", e.getMessage());
        }
    }

    @Test
    public void testEventToString_correctFormat_noException() {
        try {
            Event eve = new Event("something1", "2022-01-03 1800");
            assertEquals("[E][ ] something1 (at: Jan 03 2022 18:00)", eve.toString());
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testEventFormatSave_correctFormat_noException() {
        try {
            Event eve = new Event("something3", "2022-01-23 1547");
            assertEquals("E |0| something3 /at 2022-01-23 1547", eve.formatSave());
        } catch (DukeException e) {
            Assertions.fail();
        }
    }
}
