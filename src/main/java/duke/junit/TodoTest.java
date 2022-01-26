package duke.junit;
import duke.exception.DukeException;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test To do class for JUnit testing
 */
public class TodoTest {

    /**
     * test if string of task added is correct
     *
     * @throws DukeException when task cannot be added
     */
    @Test
    public void testToString() throws DukeException {
        Todo event1 = new Todo("test 1");
        assertEquals("[T][ ] test 1", event1.toString(), "pass");
    }

    /**
     * test if data saved of the task added is correct
     *
     * @throws DukeException when task cannot be added
     */
    @Test
    public void testToString2() throws DukeException {
        Todo event2 = new Todo("test 1");
        assertEquals("[T][ ] test 1", event2.getTaskData(), "pass");
    }
}
