package duke.junit;
import duke.exception.DukeException;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() throws DukeException {
        Todo event1 = new Todo("test 1");
        assertEquals("[T][ ] test 1", event1.toString(), "pass");
    }

    @Test
    public void testToString2() throws DukeException {
        Todo event2 = new Todo("test 1");
        assertEquals("[T][ ] test 1", event2.getTaskData(), "pass");
    }
}
