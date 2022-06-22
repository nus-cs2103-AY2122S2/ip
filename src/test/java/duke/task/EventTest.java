package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getAt() {
        Event testEvent = new Event("project meeting", "Mon 2-4pm");
        assertEquals("Mon 2-4pm", testEvent.getAt());
    }
}