import duke.utils.DukeException;
import duke.utils.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Test class for TaskList class
 */
public class TaskListTest {

    /**
     * Tests whether tasklist is being created
     * properly
     */
    @Test
    public void testListSize() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        try {
            tl.addToDo("work");
            assertEquals(1, tl.getSize());
        } catch (DukeException.DukeNoTaskGivenException e) {
            fail();
        }


    }
}
