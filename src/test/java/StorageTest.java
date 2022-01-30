import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;

public class StorageTest {
    @Test
    public void load_fileNotFoundException() throws IOException {
        Boolean wasThrown = false;
        File file = new File("data/test.txt");

        if (file.exists()) {
            file.delete();
        }

        Storage storage = new Storage("data/test.txt");

        try {
            storage.load();
        } catch (DukeException e) {
            wasThrown = true;
        }

        assertTrue(wasThrown);
    }


}
