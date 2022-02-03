import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;


public class StorageTest {

    private final Storage validStorage = new Storage("data/.test.dat");
    private final Storage invalidStorage = new Storage("data/.invalid.dat");

    /**
     * Test to determine if {@link Storage#read()} works as intended.
     */
    @Test
    public void readTest() {
        try {
            assertNotNull(validStorage.read());
            assertNotNull(invalidStorage.read());
        } catch (DukeException e) {
            assertEquals("Unable to locate/read data file.", e.getMessage());
        }
    }

    /**
     * Test to determine if {@link Storage#write(TaskList)} works as intended.
     */
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
