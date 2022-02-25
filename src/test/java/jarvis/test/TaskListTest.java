package jarvis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.exceptions.InvalidTaskException;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.tasks.Todo;
import jarvis.utils.Storage;

public class TaskListTest {
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
    public void createList_emptyList() {
        TaskList dl = new TaskList(taskList);
        String expected = "List is empty. Add items to the list.";
        assertEquals(expected, dl.toString());
    }

    @Test
    public void addTask_validTask() throws InvalidTaskException {
        TaskList dl = new TaskList(taskList);
        String taskString = "todo borrow book";
        Task expected = new Todo("borrow book");
        dl.add(taskString);
        Task actual = dl.getTaskList().get(0);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void markTask_validTask() throws InvalidTaskException {
        Task task = new Todo("borrow book");
        taskList.add(task);
        TaskList dl = new TaskList(taskList);
        dl.markTask(1);
        String actual = dl.getTaskList().get(0).getStatusIcon();
        String expected = "x";
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTask_validTask() throws InvalidTaskException {
        Task task = new Todo("borrow book");
        taskList.add(task);
        TaskList dl = new TaskList(taskList);
        dl.delete(1);
        int size = dl.getTaskList().size();
        assertEquals(0, size);
    }

    @Test
    public void findTask_validKeyword() {
        taskList.add(new Todo("borrow book"));
        taskList.add(new Todo("test"));
        taskList.add(new Todo("bla bla"));
        TaskList dl = new TaskList(taskList);
        String expected = "Here are the matching tasks in your list:\n1. [T] [ ] borrow book\n";
        assertEquals(expected, dl.find("book"));
    }

    @Test
    public void findTask_invalidTask() {
        taskList.add(new Todo("borrow book"));
        taskList.add(new Todo("test"));
        taskList.add(new Todo("bla bla"));
        TaskList dl = new TaskList(taskList);
        String expected = "No results found.";
        assertEquals(expected, dl.find("pineapple"));
    }
}
