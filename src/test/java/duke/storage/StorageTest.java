package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.io.Storage;




public class StorageTest {

    @Test
    public void importTasks_fileNotExist_emptyList() throws IOException, DateTimeParseException {
        assertEquals(0, new Storage(new Parser()).importTasks().getTotalTasks());
    }
}
