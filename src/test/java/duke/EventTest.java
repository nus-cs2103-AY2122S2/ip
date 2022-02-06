package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringOutput_descDateTime() {
        Event deadline = new Event("cook", "2021-01-01 1234");
        assertEquals("[E][ ] cook (at: 2021-01-01 1234)", deadline.toString());
    }
}
