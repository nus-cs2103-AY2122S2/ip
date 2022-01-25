package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The DeadlineTest class, containing tests for the Deadline class.
 *
 * @author Jet Tan
 */
public class DeadlineTest {
    @Test
    public void testStringOutput_descDateTime() {
        Deadline deadline = new Deadline("aaaa", "2022-05-11 2359");
        assertEquals("[D][ ] aaaa (by: May-11-2022 2359)", deadline.toString());
    }
}