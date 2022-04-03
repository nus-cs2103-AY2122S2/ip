package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event e = new Event("read a book","2022/12/01 2359");
        assertEquals("[E][ ] read a book (at: Dec 1 2022 23:59)", e.toString());
    }

    @Test
    public void testWriteToFile() {
        Event e = new Event("read a book","2022/12/01 2359");
        e.markComplete();
        assertEquals("E | D | read a book | Dec 1 2022 23:59", e.writeToFile());
    }
}
