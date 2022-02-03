import seedu.duke.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][X] do this - by: Jan 01 2020",
                new Deadline("do this", true, "01-01-2020").toString());
    }
}
