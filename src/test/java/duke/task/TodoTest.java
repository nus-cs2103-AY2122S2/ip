package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read a book", new Todo("read a book").toString());
    }

    @Test
    public void testWriteToFile() {
        Todo t = new Todo("read a book");
        t.markComplete();
        assertEquals("T | D | read a book", t.writeToFile());
    }

}
