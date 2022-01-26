package istjbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void constructor_nonDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("test", "nonDate");
            fail();
        } catch (Exception e) {
            assertEquals("Text 'nonDate' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("test", "2021-01-26");
        assertEquals("[D][ ] test (by: Jan 26 2021)", deadline.toString());
    }

    @Test
    public void testDeadlineToTxtString() {
        Deadline deadline = new Deadline("test", "2021-01-26");
        assertEquals("deadline / 0 / test / 2021-01-26", deadline.toTxtString());
    }
}
