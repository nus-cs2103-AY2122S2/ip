import duke.storage.TaskList;
import duke.task.Task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    @DisplayName("test task list")
    void testTaskList() {
        TaskList tl = new TaskList();
        int taskListSize = tl.size();
        Task newTask = new Task("test task");
        tl.add(newTask);
        assertEquals(tl.size(), taskListSize + 1);
        assertEquals(tl.get(tl.size() - 1), newTask);
        tl.remove(tl.size() - 1);
        assertEquals(tl.size(), taskListSize);
    }
}
