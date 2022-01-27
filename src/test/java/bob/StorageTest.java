package bob;

import bob.Task.Deadline;
import bob.Task.Event;
import bob.Task.Task;
import bob.Task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private Storage store;
    private TaskList taskList;
    @BeforeEach
    public void setUp() {
        store = new Storage("StorageTest.txt");
        taskList = setUpTaskList();
    }

    private TaskList setUpTaskList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("feed the dogs"));
        tasks.add(new Deadline("homework",
                LocalDateTime.parse("2022-01-31T23:59")));
        tasks.add(new Event("seminar",
                LocalDate.parse("2022-01-31"),
                LocalTime.parse("22:59"),
                LocalTime.parse("23:59")));
        return new TaskList(tasks);
    }

    @AfterEach
    public void tearDown() {
        new File("StorageTest.txt").delete();
    }

    @Test
    public void updateStoreTest() {
        store.updateStore(taskList);
        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(taskList.getList().get(i).toString(),
                    store.getSavedStore().get(i).toString());
        }
    }
}