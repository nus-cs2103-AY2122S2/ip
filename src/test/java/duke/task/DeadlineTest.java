package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineTest {
    Deadline deadline = new Deadline("test", "2000-01-01");

    @Test
    public void getName() {
        assertEquals("test", deadline.getName());
    }

    @Test
    public void getTime() {
        assertEquals(LocalDate.parse("2000-01-01"), deadline.getTime());
    }

    @Test
    public void invalidDate_wrongFormat_exceptionThrown() {
        try {
            deadline = new Deadline("test2", "02-12-2000");
            assertEquals(1,2);
        } catch (DateTimeParseException e) {
            assertEquals(2,2);
        }
    }
}
