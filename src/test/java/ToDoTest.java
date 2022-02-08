import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ToDo class
 */
public class ToDoTest {

    /**
     * Tests whether the mark
     * and unmark feature work
     * correctly
     */
    @Test
    public void testCompletionStatus(){
        ToDo task = new ToDo("Work", false);
        task.markCompleted();
        assertTrue(task.getCompletionStatus());
        task.markNotCompleted();
        assertFalse(task.getCompletionStatus());
    }

}
