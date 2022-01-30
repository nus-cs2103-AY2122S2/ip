import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.data.task.EventTask;

/**
 * Tests the event task.
 */
public class EventTaskTest {
    /**
     * Test that the event task can be constructed correctly,
     * and its methods work correctly.
     */
    @Test
    public void eventTask_constructor() {
        String eventString = "[E][ ] Chicken party (by: Oct 15 2019)";
        EventTask task = new EventTask("Chicken party", "2019-10-15");

        // correctly prints description, formatted date and completion status
        assertEquals(task.toString(), eventString);

        // invalid date format throws exception
        assertThrows(DateTimeParseException.class, () -> {
            new EventTask("Chicken party", "15-10-2022");
        });
    }
}
