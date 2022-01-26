package duke.junit;
import duke.exception.DukeException;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Task class for JUnit testing
 */
public class TaskTest {

    /**
     * Test for format of string outputted.
     *
     * @throws DukeException when task cannot be created.
     */
    @Test
    public void testToString() throws DukeException {
        Task event1 = new Task("test 1");
        assertEquals("[ ] test 1", event1.toString(), "pass");
    }

    /**
     * Test for format of string data saved.
     *
     * @throws DukeException when task cannot be created.
     */
    @Test
    public void testToString2() throws DukeException {
        Task event2 = new Task("test 1");
        assertEquals("[ ] test 1", event2.getTaskData(), "pass");
    }

    /**
     * Test for format of task description.
     *
     * @throws DukeException when task cannot be created.
     */
    @Test
    public void testToString3() throws DukeException {
        Task event3 = new Task("test 1");
        assertEquals("test 1", event3.getDescription(), "pass");
    }

    /**
     * Test for format of task status if is correct.
     *
     * @throws DukeException when task cannot be created.
     */
    @Test
    public void testToString4() throws DukeException {
        Task event4 = new Task("test 1");
        assertEquals(" ", event4.getStatus(), "pass");
    }
}
