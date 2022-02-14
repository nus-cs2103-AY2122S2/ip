import duke.task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Test class for Event Class
 */
public class EventTest {

    /**
     * Tests whether dates are stored correctly
     * within the Event objects
     */
    @Test
    public void testDateFormat(){
        Event eve = new Event("event", false, LocalDate.parse("1999-01-01"));
        assertEquals("Jan 1 1999", eve.getDate());
    }

}
