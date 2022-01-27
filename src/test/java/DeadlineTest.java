import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Deadline class.
 */
public class DeadlineTest {

    /**
     * Correct format of marked Deadline.
     */
    @Test
    public void toString_markedDeadline_success() {
        Deadline deadline = new Deadline("deadline1",
                LocalDateTime.parse("2022-02-27T18:00"));
        deadline.markAsDone();
        assertEquals(deadline.toString(),
                "[D][X] deadline1 (by: 6:00 PM Feb 27 2022)");
    }
}