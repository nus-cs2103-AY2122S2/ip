package duke.test;

import duke.DukeList;
import duke.Storage;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DukeListTest {
    private static final Storage storage = new Storage();

    @Test
    public void createListTest() {
        ArrayList<Task> taskList = new ArrayList<>();
        DukeList dl = new DukeList(taskList);
        String expected = "List is empty. Add items to the list.";
        assertEquals(expected, dl.toString());
    }

    @Test
    public void addTaskTest() throws InvalidTaskException {
        ArrayList<Task> taskList = new ArrayList<>();
        DukeList dl = new DukeList(taskList);
        String taskString = "todo borrow book";
        Task expected = new Todo("borrow book");
        dl.add(taskString);
        Task actual = dl.getTaskList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void markTaskTest() throws InvalidTaskException {
        Task task = new Todo("borrow book");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        DukeList dl = new DukeList(taskList);
        dl.markTask(1);
        String actual = dl.getTaskList().get(0).getStatusIcon();
        String expected = "x";
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTaskTest() throws InvalidTaskException {
        Task task = new Todo("borrow book");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        DukeList dl = new DukeList(taskList);
        dl.delete(1);
        int size = dl.getTaskList().size();
        assertEquals(0, size);
    }
}
