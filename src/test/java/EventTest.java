import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import luca.task.Event;

/**
 * Tests the Event class.
 */
public class EventTest {

    /**
     * Correct format of marked Event.
     */
    @Test
    public void toString_markedEvent_success() {
        Event event = new Event("event1",
                LocalDateTime.parse("2022-02-27T18:00"),
                LocalDateTime.parse("2022-02-27T19:00"));
        event.markAsDone();
        assertEquals(event.toString(),
                "[E][X] event1 (at: 6:00 PM - 7:00 PM Feb 27 2022)");
    }
}
