package chatbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test public void testToString() {
        Deadline deadline = new Deadline("My Deadline", LocalDate.parse("2022-01-27"), LocalTime.parse("23:59:59"));
        assertEquals("[D][ ] My Deadline (by: 2022-01-27 23:59:59)", deadline.toString());
    }

    @Test public void testSetDone() {
        Deadline deadline = new Deadline("My Deadline", LocalDate.parse("2022-01-27"), LocalTime.parse("23:59:59"));
        deadline.setDone(true);
        assertEquals("[D][X] My Deadline (by: 2022-01-27 23:59:59)", deadline.toString());
        deadline.setDone(false);
        assertEquals("[D][ ] My Deadline (by: 2022-01-27 23:59:59)", deadline.toString());
    }
}