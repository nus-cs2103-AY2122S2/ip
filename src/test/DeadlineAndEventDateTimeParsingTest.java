import duke.action.Deadline;
import duke.action.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineAndEventDateTimeParsingTest {
    @Test
    public void testDeadlineConstructor() {
        try {
            new Deadline("task", "2010-06-06 10:10");
            new Deadline("task", "9999-12-28 24:00");
        } catch (Exception e) {
            assertEquals("no error", "error");
        }
    }

    @Test
    public void testEventConstructor() {
        try {
            new Event("task", "2010-06-06 10:10");
            new Event("task", "9999-12-28 24:00");
        } catch (Exception e) {
            assertEquals("no error", "error");
        }
    }
}
