package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline d = new Deadline("assignment 1", "2022-02-07");
        assertEquals("[D][ ] assignment 1 (by: Feb 07 2022)", d.toString());
    }

    @Test
    void getSaveFormat() {
        Deadline d = new Deadline("assignment 1", "2022-02-07");
        assertEquals("D,0,assignment 1,2022-02-07\n", d.getSaveFormat());
    }
}