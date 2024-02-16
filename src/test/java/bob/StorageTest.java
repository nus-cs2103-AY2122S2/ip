package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;


class StorageTest {
    private Storage store;
    private File file;
    private TaskList taskList;
    @BeforeEach
    public void setUp() {
        store = new Storage("StorageTest.txt");
        file = new File("StorageTest.txt");
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

    @SuppressWarnings("unchecked")
    @Test
    public void updateStore() {
        store.updateStore(taskList);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Task> tasks = (List<Task>) ois.readObject();
            for (int i = 0; i < taskList.size(); i++) {
                assertEquals(taskList.getList().get(i).toString(),
                        tasks.get(i).toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
