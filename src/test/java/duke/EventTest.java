package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        assertEquals("[E][X] attend lecture (at: Jan 23 2022)", new Event(" attend lecture", true, "2022-01-23").toString());
    }
}
