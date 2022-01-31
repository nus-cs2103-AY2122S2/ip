package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidOperationException;

public class DeadlineTest {
    private Deadline deadline;

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] this(by: 1 MAY 2022)", new Deadline("this", "2022-05-01").toString());
    }

    @Test
    public void testStringConversion_exceptionThrown() {
        try {
            deadline = new Deadline("this", "now");
        } catch (DateTimeParseException e) {
            assertEquals("Text 'now' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void event_markDone_success() throws InvalidOperationException {
        deadline = new Deadline("this", "2022-05-01");
        deadline.mark();
        assertEquals("[D][X] this(by: 1 MAY 2022)", deadline.toString());
    }

    @Test
    public void event_markDone_exceptionThrown() throws InvalidOperationException {
        try {
            deadline = new Deadline("this", "2022-05-01");
            deadline.mark();
            assertEquals("[D][X] this(by: 1 MAY 2022)", deadline.toString());
            deadline.mark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already marked", e.toString());
        }
    }

    @Test
    public void event_unmark_success() throws InvalidOperationException {
        deadline = new Deadline("this", "2022-05-01");
        deadline.mark();
        assertEquals("[D][X] this(by: 1 MAY 2022)", deadline.toString());
        deadline.unmark();
        assertEquals("[D][ ] this(by: 1 MAY 2022)", deadline.toString());
    }

    @Test
    public void event_unmark_exceptionThrown() throws InvalidOperationException {
        try {
            deadline = new Deadline("this", "2022-05-01");
            deadline.unmark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already unmarked", e.toString());
        }
    }
}
