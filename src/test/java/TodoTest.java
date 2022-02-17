import chibot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void storageString_todoToBePlacedIntoStorageFile_correctlyFormatted() {
        Todo toTest = new Todo("some description", true);
        assertEquals(" T | 1 | some description", toTest.convertToFileFormat());
    }

}