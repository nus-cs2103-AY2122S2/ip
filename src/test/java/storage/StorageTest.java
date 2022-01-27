package storage;

import ann.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void testInvalidStorageFilePathException() {
        try {
            Storage storage = new Storage("data", "hello");
            fail();
        } catch (Exception e){
            assertEquals("Storage file should end with '.txt'", e.getMessage());
        }
    }
}
