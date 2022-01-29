import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    // Unable to test this as errors are regarding no r/w permission on the system.
    Storage validStorage = new Storage("data/.test.dat");
    Storage invalidStorage = new Storage("data/.invalid.dat");

    @Test
    public void readTest() {
        try {
            assertNotNull(validStorage.read());
            assertNotNull(invalidStorage.read());
        } catch (DukeException e) {
            assertEquals("Unable to locate/read data file.", e.getMessage());
        }
    }

    @Test
    public void writeTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("write test"));
        try {
            assertTrue(validStorage.write(tasks));
            assertTrue(invalidStorage.write(tasks));
        } catch (DukeException e) {
            assertEquals("Unable to locate/write to data file.", e.getMessage());
        }
    }
}
