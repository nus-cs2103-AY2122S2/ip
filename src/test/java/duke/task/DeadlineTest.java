package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() throws DukeException {
        Deadline dl = Deadline.createDeadline("deadline finish essay /by 2019-12-12");
        assertEquals("[D][ ] finish essay (by: Dec 12 2019)", dl.toString());
    }

    @Test
    public void testDeadlineMark() throws DukeException {
        Deadline dl = Deadline.createDeadline("deadline finish essay /by 2020-11-11");
        dl.markTask();
        assertEquals("[D][X] finish essay (by: Nov 11 2020)", dl.toString());
    }

}
