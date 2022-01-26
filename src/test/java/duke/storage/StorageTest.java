package duke.storage;

import duke.io.Storage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void importTasks_fileNotExist_emptyList() throws IOException, DateTimeParseException {
        assertEquals(0,new Storage().importTasks().getTotalTasks());
    }
}
