package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList tasks = new TaskList();

    @Test
    public void size_success(){
        tasks.add(new ToDo("foo"));
        assertEquals(1, tasks.size());
    }
}
