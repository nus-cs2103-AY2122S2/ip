import chibot.storage.Storage;
import chibot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StorageTest {

    @Test
    void todoCanBeRetrievedFromStorageString_testTodoString_newTodoCreated() {
        String simulateTaskInStorage = " T | 0 | teeth";
        Storage toTest = new Storage("/dummy/someFile.txt");
        assertEquals(new Todo("teeth", false).toString(),
                toTest.convertFileDataToTask(simulateTaskInStorage).toString());
    }

    @Test
    void someProblemWithTheStoredTask_badFormatStoredTask_nullReturned() {
        String simulateTaskInStorage = " T | 4 | very bad";
        Storage toTest = new Storage("/dummy/someFile.txt");
        assertNull(toTest.convertFileDataToTask(simulateTaskInStorage));
    }

}