import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.tasks.Deadline;

/**
 * Test deadline class for JUnit testing
 */
public class DeadlineTest {
    /**
     * test if string of task added is correct
     *
     * @throws DukeException when task cannot be added
     */
    @Test
    public void testToString() throws DukeException {
        Deadline deadline1 = new Deadline("test 1", "2022-01-01 10:00");
        assertEquals("[D][ ] test 1 (by: Jan 1 2022 10:00 am)", deadline1.toString(), "pass");
    }

    /**
     * test if string of task data stored is correct
     *
     * @throws DukeException when task data cannot be added
     */
    @Test
    public void testToString2() throws DukeException {
        Deadline deadline1 = new Deadline("test 1", "2022-01-01 10:00");
        assertEquals("[D][ ] test 1 (by: 2022-01-01 10:00)", deadline1.getTaskData(), "pass");
    }

}
