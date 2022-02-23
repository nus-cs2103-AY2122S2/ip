package sparrow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sparrow.logic.task.ToDo;
import sparrow.model.TaskList;

public class TaskListTest {
    private final TaskList tasks = new TaskList();

    @Test
    public void size_success() {
        tasks.add(new ToDo("foo"));
        assertEquals(1, tasks.size());
    }
}
