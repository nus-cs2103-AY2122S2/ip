package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("zoom meeting", " 2022-01-22");
        assertEquals("[E] [ ] zoom meeting(at: Jan 22 2022)", event.toString());
    }

    @Test
    public void testSaveFormat() {
        Event event = new Event("zoom meeting", " 2022-02-11");
        assertEquals("E ### 0 ### zoom meeting ###  2022-02-11", event.saveFormat());
    }
}