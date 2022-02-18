package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getDetails_newDeadlineCreated_returnStringArray() {
        Event event = new Event("Description", "time");
        String[] expected = new String[]{"EVENT", "0", "Description", "time"};
        String[] actual = event.getDetails();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }
}
