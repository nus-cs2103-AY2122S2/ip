package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString_withDateTime() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        assertEquals("[D][ ] x (by: Dec 12 2077 12:00)", task.toString());
    }

    @Test
    public void testToString_withDate() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), null);
        assertEquals("[D][ ] x (by: Dec 12 2077)", task.toString());
    }

    @Test
    public void testToString_exceptionThrown() {
        try {
            Deadline task = new Deadline("x", null, null);
            task.toString();
            fail();
        } catch (Exception e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void testGetStatusIcon_unmarked() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon_marked() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        assertEquals("x", task.getDescription());
    }

    @Test
    public void testSetStatus_true() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testSetStatus_false() {
        Deadline task = new Deadline("x", LocalDate.parse("2077-12-12"), LocalTime.parse("12:00"));
        task.setStatus(false);
        assertEquals(" ", task.getStatusIcon());
    }
}
