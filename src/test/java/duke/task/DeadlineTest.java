package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline d = new Deadline("read a book","2022/12/01 2359");
        assertEquals("[D][ ] read a book (by: Dec 1 2022 23:59)", d.toString());
    }

    @Test
    public void testWriteToFile() {
        Deadline d = new Deadline("read a book","2022/12/01 2359");
        d.markComplete();
        assertEquals("D | D | read a book | Dec 1 2022 23:59", d.writeToFile());
    }
}
