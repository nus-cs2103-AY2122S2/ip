package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testFileFormatConversion() {
        assertEquals("E | 0 | this week lecture | Jan 28 2022",
                new Event("this week lecture", "2022-01-28").convertToFileFormat());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] this week lecture (at: Jan 28 2022)",
                new Event("this week lecture", "2022-01-28").toString());
    }
}
