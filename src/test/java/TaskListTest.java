import dazz.TaskList;
import dazz.Ui;
import dazz.exception.DazzException;
import dazz.exception.ErrorType;
import dazz.task.Deadline;
import dazz.task.Event;
import dazz.task.Task;
import dazz.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void test_addTask_success() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("study"));
            tasks.add(new Deadline("return book", Ui.toLocalDateTime("12/12/2021 1800")));
            TaskList taskList = new TaskList(tasks);
            taskList.add(new Event("meeting", Ui.toLocalDateTime("12/12/2021 1800")));
            assertEquals(3, taskList.getSize());
        } catch (DazzException e) {}
    }

    @Test
    public void test_markTask_success() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("study"));
            tasks.add(new Deadline("return book", Ui.toLocalDateTime("12/12/2021 1800")));
            TaskList taskList = new TaskList(tasks);
            taskList.mark(1);
            assertTrue(taskList.getTask(1).getIsDone());
        } catch (DazzException e) {}
    }

    @Test
    public void test_unmarkTask_success() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("study", true));
            tasks.add(new Event("meeting", Ui.toLocalDateTime("12/12/2021 1800")));
            TaskList taskList = new TaskList(tasks);
            taskList.unmark(1);
            assertFalse(taskList.getTask(1).getIsDone());
        } catch (DazzException e) {}
    }

    @Test
    public void test_markTask_exceptionThrown() {
        try {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Todo("study"));
            tasks.add(new Deadline("return book", Ui.toLocalDateTime("12/12/2021 1800")));
            TaskList taskList = new TaskList(tasks);
            taskList.mark(5);
        } catch (DazzException e) {
            assertEquals(ErrorType.INVALID_INDEX.getErrorMessage(), e.getMessage());
        }
    }
}
