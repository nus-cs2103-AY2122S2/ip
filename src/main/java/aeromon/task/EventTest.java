package aeromon.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toString_normal_success() {
        Event event = new Event("Outing", LocalDate.of(2022, 2, 14));
        assertEquals("[E][ ] Outing (at: Feb 14 2022)", event.toString());
    }

    @Test
    public void toString_marked_indicated() {
        Event event = new Event("Outing", LocalDate.of(2022, 2, 14));
        event.markAsDone();
        assertEquals("[E][X] Outing (at: Feb 14 2022)", event.toString());
    }

    @Test
    public void toOutputFormat_normal_success() {
        Event event = new Event("Outing", LocalDate.of(2022, 2, 14));
        assertEquals("E / 0 / Outing / Feb 14 2022", event.toOutputFormat());
    }

    @Test
    public void toOutputFormat_marked_indicated() {
        Event event = new Event("Outing", LocalDate.of(2022, 2, 14));
        event.markAsDone();
        assertEquals("E / 1 / Outing / Feb 14 2022", event.toOutputFormat());
    }
}
