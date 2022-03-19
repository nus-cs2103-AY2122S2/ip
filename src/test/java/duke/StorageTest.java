package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;

public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setUp() {
        String home = System.getProperty("user.dir");
        Path directory = Paths.get(home, "duketest");
        Path filePath = Paths.get(home, "duketest", "data.txt");
        Path contactsPath = Paths.get(home, "duketest", "contacts.txt");
        storage = new Storage(directory, filePath, contactsPath);
    }

    @Test
    public void loadDeadlineTest() throws DukeException {
        Task task = storage.constructTask("D,false,Do homework,2022-02-21 23:59");
        assertEquals(task.toString(), "[D][ ] Do homework (by: Feb 21 2022 23:59)");
    }

    @Test
    public void loadEventTest() throws DukeException {
        Task task = storage.constructTask("E,false,CS2103T lecture,2022-02-21 12:00");
        assertEquals(task.toString(), "[E][ ] CS2103T lecture (at: Feb 21 2022 12:00)");
    }

    @Test
    public void loadTodoTest() throws DukeException {
        Task task = storage.constructTask("T,false,Do homework");
        assertEquals(task.toString(), "[T][ ] Do homework");
    }

}
