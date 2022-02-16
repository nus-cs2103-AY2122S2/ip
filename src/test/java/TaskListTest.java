import duke.exceptions.DukeException;
import duke.system.TaskList;
import duke.tasks.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListTest() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.add(new ToDoTask("read"));
        assertEquals(taskList.tasksAsString(),"     1. [T][ ] read\n");
    }
}
