package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event e = new Event("project meeting", "Sat 2pm");
        assertEquals("[E][ ] project meeting (at: Sat 2pm)", e.toString());
    }

    @Test
    void getSaveFormat() {
        Event e = new Event("project meeting", "Sat 2pm");
        assertEquals("E,0,project meeting,Sat 2pm\n", e.getSaveFormat());
    }
}