package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The DeadlineTest class tests the functionality of the Deadline class.
 *
 * @author Rdac0
 */
public class DeadlineTest {
    Deadline deadline = new Deadline("test", "2000-01-01");

    /**
     * Tests getName functionality.
     */
    @Test
    public void getName() {
        assertEquals("test", deadline.getName());
    }

    /**
     * Tests getTime functionality. should return a LocalDate
     */
    @Test
    public void getTime() {
        assertEquals(LocalDate.parse("2000-01-01"), deadline.getTime());
    }

    /**
     * Tests whether Deadline accepts incorrect format.
     * It should throw an exception.
     */
    @Test
    public void invalidDate_wrongFormat_exceptionThrown() {
        try {
            deadline = new Deadline("test2", "02-12-2000");
            // fail
            assertEquals(1,2);
        } catch (DateTimeParseException e) {
            // pass
            assertEquals(2,2);
        }
    }
}
