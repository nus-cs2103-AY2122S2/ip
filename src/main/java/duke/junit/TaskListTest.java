package duke.junit;
import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test TaskList class for JUnit testing
 */
public class TaskListTest {
    /**
     * Test for the size of list after a list is created.
     *
     * @throws DukeException when list cannot be created
     */
    @Test
    public void testListSize() throws DukeException {
        TaskList event1 = new TaskList();
        assertEquals(0, event1.getSize(), "pass");
    }

    /**
     * Test for the size of list after adding a task.
     *
     * @throws DukeException when list cannot be created
     */
    @Test
    public void testListSize2() throws DukeException {
        TaskList event2 = new TaskList();
        event2.addTask(new Task("hi"));
        assertEquals(1, event2.getSize(), "pass");
    }
}
