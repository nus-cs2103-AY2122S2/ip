package myboss;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void testClearTaskFile() {
        Storage s = new Storage("./test/StorageTest.txt");
        assertEquals(s.clearTaskFile(),true);
    }

    @Test
    void testAppendTaskToFile() throws IOException {
        Storage s = new Storage("./test/StorageTest.txt");
        assertEquals(s.appendTaskToFile(new ToDo("Test Todo 1")),true);
        assertEquals(s.appendTaskToFile(new Event("Test Event 2", "2023-10-01 2pm")),true);
        assertEquals(s.appendTaskToFile(new Deadline("Test Deadline 3", "2023-10-02")),true);
    }

    @Test
    void testLoadTaskListFromFile() {
        Storage s = new Storage("./test/StorageTest.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("Test Todo 1"));
        taskList.add(new Event("Test Event 2", "2023-10-01 2pm"));
        taskList.add(new Deadline("Test Deadline 3", "2023-10-02"));
        assertEquals(s.loadTaskListFromFile(),taskList);
    }
}