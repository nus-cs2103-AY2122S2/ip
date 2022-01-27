package chatbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test public void testToString() {
        Event task = new Event("My Event", LocalDate.parse("2022-01-27"), LocalTime.parse("23:59:59"),
                LocalDate.parse("2022-01-29"), LocalTime.parse("14:44"));
        assertEquals("[E][ ] My Event (at: 2022-01-27 23:59:59 to 2022-01-29 14:44)", task.toString());
    }

    @Test public void testSetDone() {
        Event task = new Event("My Event", LocalDate.parse("2022-01-27"), LocalTime.parse("23:59:59"),
                LocalDate.parse("2022-01-29"), LocalTime.parse("14:44"));
        task.setDone(true);
        assertEquals("[E][X] My Event (at: 2022-01-27 23:59:59 to 2022-01-29 14:44)", task.toString());
        task.setDone(false);
        assertEquals("[E][ ] My Event (at: 2022-01-27 23:59:59 to 2022-01-29 14:44)", task.toString());
    }
}