package duke.task;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTodoTask_validTask_success() {
        TaskList taskList = new TaskList();
        taskList.addToDo("Sample Task");
        assertEquals(1, taskList.getTotalTasks());

    }

}
