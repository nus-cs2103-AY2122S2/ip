package siri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] Jane's Wedding (at: Oct 10th 2019)", new Event("Jane's Wedding", "E", "Oct 10th 2019").toString());
    }
}
