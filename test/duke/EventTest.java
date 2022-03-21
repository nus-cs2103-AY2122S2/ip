package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("sleep", "2022-02-16");
        assertEquals("[E][ ] sleep (at:Wednesday, 16 February 2022)", event.toString());
    }
}