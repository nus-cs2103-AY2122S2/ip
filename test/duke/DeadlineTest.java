package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline dl = new Deadline("sleep", "2022-02-16");
        assertEquals("[D][ ]sleep (by:Wednesday, 16 February 2022)", dl.toString());
    }
}