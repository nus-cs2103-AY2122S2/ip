import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.Event;

public class EventTest {
    @Test
    public void testToString() {
        LocalDate date = LocalDate.parse("2022-04-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime beginTime = LocalTime.parse("10:45");
        LocalTime endTime = LocalTime.parse("18:25");
        Event testEvent = new Event("Testing things", date, beginTime, endTime);
        testEvent.markDone();

        String correctString = "[E][X]" + " Testing things" + " (at: " + "Apr 1 2022 from 10:45 AM to 06:25 PM" + ")";

        assertEquals(correctString, testEvent.toString());
    }
}
