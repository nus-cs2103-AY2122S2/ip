package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringOutput_descDateTime() {
        Deadline deadline = new Deadline("cook", "2021-01-01 1234");
        assertEquals("[D][ ] cook (by: 2021-01-01 1234)", deadline.toString());
    }
}
