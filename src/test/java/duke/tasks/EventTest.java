package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidOperationException;


public class EventTest {
    private Event event;

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] this(at: 1 MAY 2022)", new Event("this", "2022-05-01").toString());
    }

    @Test
    public void testStringConversion_exceptionThrown() {
        try {
            event = new Event("this", "now");
        } catch (DateTimeParseException e) {
            assertEquals("Text 'now' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void event_markDone_success() throws InvalidOperationException {
        event = new Event("this", "2022-05-01");
        event.mark();
        assertEquals("[E][X] this(at: 1 MAY 2022)", event.toString());
    }

    @Test
    public void event_markDone_exceptionThrown() throws InvalidOperationException {
        try {
            event = new Event("this", "2022-05-01");
            event.mark();
            assertEquals("[E][X] this(at: 1 MAY 2022)", event.toString());
            event.mark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already marked", e.toString());
        }
    }

    @Test
    public void event_unmark_success() throws InvalidOperationException {
        event = new Event("this", "2022-05-01");
        event.mark();
        assertEquals("[E][X] this(at: 1 MAY 2022)", event.toString());
        event.unmark();
        assertEquals("[E][ ] this(at: 1 MAY 2022)", event.toString());
    }

    @Test
    public void event_unmark_exceptionThrown() throws InvalidOperationException {
        try {
            event = new Event("this", "2022-05-01");
            event.unmark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already unmarked", e.toString());
        }
    }
}
