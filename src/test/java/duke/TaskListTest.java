package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class TaskListTest {
    private TaskList tasks = new TaskList();

    @Test
    public void size_success() {
        tasks.add(new ToDo("foo"));
        assertEquals(1, tasks.size());
    }
}
