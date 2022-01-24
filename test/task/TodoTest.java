package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        Todo t = new Todo("eat");
        assertEquals("[T][ ] eat", t.toString());
    }

    @Test
    void getSaveFormat() {
        Todo t = new Todo("eat");
        assertEquals("T,0,eat", t.getSaveFormat());
    }
}