import duke.Storage;
import duke.TaskList;
import duke.common.Constant;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTaskCreate() {
        TaskList taskList = new TaskList(new Storage("test.txt"));
        taskList.addTask("test", Task.Type.TODO);
        assertEquals("[T][ ] test", taskList.getTaskDescription(1));

        taskList.addTask("test",
                LocalDateTime.parse("May 5 2022 23:59", Constant.OUT_TIME_FORMATTER),Task.Type.EVENT);
        assertEquals("[E][ ] test(at: May 5 2022 23:59)", taskList.getTaskDescription(2));

        taskList.addTask("test",
                LocalDateTime.parse("May 5 2022 23:59", Constant.OUT_TIME_FORMATTER),Task.Type.DEADLINE);
        assertEquals("[D][ ] test(by: May 5 2022 23:59)", taskList.getTaskDescription(3));
    }

    @Test
    public void testSize() {
        TaskList taskList = new TaskList(new Storage("test.txt"));
        taskList.addTask("test", Task.Type.TODO);
        assertEquals("1", String.valueOf(taskList.size()));
    }

    @Test
    public void testDelete() {
        TaskList taskList = new TaskList(new Storage("test.txt"));
        taskList.addTask("test", Task.Type.TODO);
        taskList.addTask("test", Task.Type.TODO);
        taskList.delete(2);
        assertEquals("1", String.valueOf(taskList.size()));
    }

}
