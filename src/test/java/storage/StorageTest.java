package storage;

import exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void readTaskFile_normalInput_success() throws IOException, InvalidInputException {
        Storage storage = new Storage();

        LocalDateTime dateTime = LocalDateTime.of(2019,
                Month.DECEMBER, 3, 18, 00, 00);
        Deadline deadline = new Deadline("return book", true, dateTime);
        Event event = new Event("project meeting", dateTime);
        ToDo todo = new ToDo("read book", true);

        ListTask expected = new ListTask();

        expected.addTask(todo);
        expected.addTask(deadline);
        expected.addTask(event);

        String DEFAULT_FILEPATH = "/java/test.txt";
        String home = System.getProperty("user.dir");
        String path = home + DEFAULT_FILEPATH;

        List<Task> tasks = storage.readTaskFile(path);
        ListTask result = new ListTask(tasks);
        assertEquals(expected.generateTaskList(), result.generateTaskList());
    }


    @Test
    public void readTaskFile_invalidPath_exceptionThrown() {
        Storage storage = new Storage();

        String DEFAULT_FILEPATH = "/java/invalid.txt";
        String home = System.getProperty("user.dir");
        String path = home + DEFAULT_FILEPATH;

        FileNotFoundException thrown1 = assertThrows(FileNotFoundException.class, () -> {
            storage.readTaskFile(path);
        }, "InvalidInputException was expected");
        assertEquals("File not found under " + path, thrown1.getMessage());
    }
}
