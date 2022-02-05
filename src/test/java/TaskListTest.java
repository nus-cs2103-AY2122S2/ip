import duke.TaskList;
import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_Todo() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("CS2103T iP");
        tasks.add(todo);
        String expected = "CS2103T iP";
        assertEquals(expected, tasks.get(0).getDescription());
    }
    @Test
    public void update_Todo() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("CS2103T iP");
        tasks.add(todo);
        tasks.update(1, "mark");
        String expected = "X";
        assertEquals(expected, tasks.get(0).getStatusIcon());
    }
}
