import seedu.duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][X] attend this - at: Jan 01 2020",
                new Event("attend this", true, "01-01-2020").toString());
    }
}
