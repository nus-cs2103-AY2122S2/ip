package heylo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_validEvent_success() {
        Event event = new Event("event-1", "2022-02-09");
        assertEquals(" [E][ ] event-1\t (at Feb 9 2022)", event.toString());
    }

    @Test
    public void toString_invalidDuration_handleError() {
        Event event = new Event("event-1", "not-a-date");
        assertEquals(" [E][ ] event-1\t (at )", event.toString());
    }
}
