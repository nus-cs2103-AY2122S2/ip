import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import tasks.Tasklist;
import tasks.Todo;

public class TasklistTest {
    Tasklist tasklist = new Tasklist();

    @Test
    public void addTasks_markMarkedTasks_exceptionThrown() {
        tasklist.addTask(new Todo("eat"));
        tasklist.addTask(new Todo("sleep"));
        tasklist.addTask(new Todo("drink"));
        try {
            tasklist.markTask(2);
            tasklist.markTask(2);
            fail();
        } catch (DukeException err) {
            assertEquals("This task is already done!", err.getMessage());
        }
    }
}
