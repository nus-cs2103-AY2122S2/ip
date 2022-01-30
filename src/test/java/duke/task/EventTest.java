package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString() {
        Event e = new Event("Exams ", "Night");
        assertEquals("Exams (at: Night)", e.toString());
    }

    @Test
    void track() {
        Event e = new Event("blah", "no time");
        assertEquals("[E]", e.track());
    }

    @Test
    void getName() {
        Event e = new Event("test", "up to you");
        assertEquals("test", e.getName());
    }

    @Test
    void getTime() {
        Event e = new Event("anything", "your choice");
        assertEquals("your choice", e.getTime());
    }
}
