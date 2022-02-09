package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void getDescription_sampleEvent_matchExpected() {
        Event event = new Event("Test Event", "Mon 2-4pm");
        String expectedDescription = "[E][ ] Test Event (at: Mon 2-4pm)";
        assertEquals(expectedDescription, event.getDescription());
    }

    @Test
    void encodeTaskData_sampleEvent_matchExpected() {
        Event event = new Event("Test Event", "Mon 2-4pm");
        String expectedEncoding = "E,Test Event,N,Mon 2-4pm";
        assertEquals(expectedEncoding, event.encodeTaskData());
    }
}
