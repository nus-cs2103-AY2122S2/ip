package duke.test;

import duke.DukeList;
import duke.Storage;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DukeListTest {
    private static Storage storage;
    private ArrayList<Task> taskList;

    @BeforeAll
    static void initAll() {
        storage = new Storage();
    }

    @BeforeEach
    void init() {
        taskList = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @Test
    public void createListTest() {
        DukeList dl = new DukeList(taskList);
        String expected = "List is empty. Add items to the list.";
        assertEquals(expected, dl.toString());
    }

    @Test
    public void addTaskTest() throws InvalidTaskException {
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
        taskList.add(task);
        DukeList dl = new DukeList(taskList);
        dl.delete(1);
        int size = dl.getTaskList().size();
        assertEquals(0, size);
    }

    @Test
    public void findTaskTest() {
        taskList.add(new Todo("borrow book"));
        taskList.add(new Todo("test"));
        taskList.add(new Todo("bla bla"));
        DukeList dl = new DukeList(taskList);
        String expected = "Here are the matching tasks in your list:\n1. [T] [ ] borrow book\n";
        assertEquals(expected, dl.find("book"));
    }

    @Test
    public void findInvalidTaskTest() {
        taskList.add(new Todo("borrow book"));
        taskList.add(new Todo("test"));
        taskList.add(new Todo("bla bla"));
        DukeList dl = new DukeList(taskList);
        String expected = "No results found.";
        assertEquals(expected, dl.find("pineapple"));
    }
}
