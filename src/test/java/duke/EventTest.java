package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The EventTest class, containing tests for the Event class.
 *
 * @author Jet Tan
 */
public class EventTest {
    @Test
    public void testStringOutput_descDateTime() {
        Event event = new Event("aaaa", "2022-05-11 2359");
        assertEquals("[E][ ] aaaa (at: May-11-2022 2359)", event.toString());
    }
}