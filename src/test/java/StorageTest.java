import duke.DukeException;
import duke.Storage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    public void load_FileNotFoundException() throws IOException {
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
