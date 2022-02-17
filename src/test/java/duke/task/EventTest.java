package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void dummyEvent() {
        LocalDate date = LocalDate.parse("2022-01-21");
        Event event = new Event("Test Event", date);

        assertEquals("2022-01-21", event.getDate().toString());
    }

    @Test
    public void testToString() {
        LocalDate date = LocalDate.parse("2022-01-21");
        Event event = new Event("Test Event", date);

        assertEquals("[E][ ] Test Event (at: Jan 21 2022)", event.toString());
    }
}
