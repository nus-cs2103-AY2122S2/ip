package duke.junit;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() throws DukeException {
        Deadline deadline1 = new Deadline("test 1", "2022-01-01 10:00");
        assertEquals("[D][ ] test 1 (by: Jan 1 2022 10:00 am)", deadline1.toString(), "pass");
    }

    @Test
    public void testToString2() throws DukeException {
        Deadline deadline1 = new Deadline("test 1", "2022-01-01 10:00");
        assertEquals("[D][ ] test 1 (by: 2022-01-01 10:00)", deadline1.getTaskData(), "pass");
    }

}
