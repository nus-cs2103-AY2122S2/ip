package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("D | 0 | this week iP | Jan 27 2022",
                new Deadline("this week iP", "2022-01-27").convertToFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] this week iP (by: Jan 27 2022)",
                new Deadline("this week iP", "2022-01-27").toString());
    }
}
