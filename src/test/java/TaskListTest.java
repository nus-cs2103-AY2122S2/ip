import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class TaskListTest {

    @Test
    public void taskListSize() {
        TaskList taskList = new TaskList();
        taskList.add(new Deadline("one", false, null, LocalDate.parse("2020-02-02")));
        taskList.add(new Event("two", true, 1, LocalDate.parse("2021-01-02")));
        taskList.add(new Todo("three", true, 3));

        assertEquals(taskList.size(), 3);
    }

    @Test
    public void taskListDelete() {
        TaskList taskList = new TaskList();
        taskList.add(new Deadline("one", false, null, LocalDate.parse("2020-02-02")));
        taskList.add(new Event("two", true, 1, LocalDate.parse("2021-01-02")));
        taskList.add(new Todo("three", true, 3));

        taskList.delete(3);
        assertEquals(taskList.size(), 2);

    }
}
