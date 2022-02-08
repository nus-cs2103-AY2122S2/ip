package lily.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void testToString() {
        String t = new Todo("hi").toString();
        assertEquals(t, "[T][ ] hi");
    }
}