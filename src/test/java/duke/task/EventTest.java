package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString_withDateStartEndTime() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        assertEquals("[E][ ] x (at: Dec 12 2077 12:00-13:00)", task.toString());
    }

    @Test
    public void testToString_withDateStartTime() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), null);
        assertEquals("[E][ ] x (at: Dec 12 2077 12:00)", task.toString());
    }

    @Test
    public void testToString_withDate() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                null, null);
        assertEquals("[E][ ] x (at: Dec 12 2077)", task.toString());
    }

    @Test
    public void testToString_exceptionThrown() {
        try {
            Event task = new Event("x", null,
                    null, null);
            task.toString();
            fail();
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void testGetStatusIcon_unmarked() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon_marked() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        assertEquals("x", task.getDescription());
    }

    @Test
    public void testSetStatus_true() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testSetStatus_false() {
        Event task = new Event("x", LocalDate.parse("2077-12-12"),
                LocalTime.parse("12:00"), LocalTime.parse("13:00"));
        task.setStatus(false);
        assertEquals(" ", task.getStatusIcon());
    }
}
