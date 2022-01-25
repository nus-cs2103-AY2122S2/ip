package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("T | 0 | this week iP", new Todo("this week iP").convertToFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][X] this week iP", new Todo("this week iP", true).toString());
    }
}
