package duke.junit;

import duke.exception.DukeException;
import duke.tasks.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() throws DukeException {
        Task event1 = new Task("test 1");
        assertEquals("[ ] test 1", event1.toString(), "pass");
    }

    @Test
    public void testToString2() throws DukeException {
        Task event2 = new Task("test 1");
        assertEquals("[ ] test 1", event2.getTaskData(), "pass");
    }

    @Test
    public void testToString3() throws DukeException {
        Task event3 = new Task("test 1");
        assertEquals("test 1", event3.getDescription(), "pass");
    }

    @Test
    public void testToString4() throws DukeException {
        Task event4 = new Task("test 1");
        assertEquals(" ", event4.getStatus(), "pass");
    }
}
