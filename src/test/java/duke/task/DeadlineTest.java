package duke.task;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeadlineTest uses an assert, to verify correct values of Deadline creation.
 */
public class DeadlineTest {
    @Test
    public void testDeadlines() {
        assertEquals("[D][ ] finish assignment (by: Jan 1st)",
                new Deadlines("finish assignment", "Jan 1st").toString());
    }
}
