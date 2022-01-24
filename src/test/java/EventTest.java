import duke.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        LocalDate testDate = LocalDate.parse("2022-04-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime testTimeBegin = LocalTime.parse("10:45");
        LocalTime testTimeEnd = LocalTime.parse("18:25");
        Event testEvent = new Event("Testing things", testDate, testTimeBegin, testTimeEnd);
        testEvent.markDone();

        String correctString = "[E][X]" + " Testing things" + " (at: " + "Apr 1 2022 from 10:45 AM to 06:25 PM" + ")";

        assertEquals(correctString, testEvent.toString());
    }
}
