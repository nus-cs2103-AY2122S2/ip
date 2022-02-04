package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ann.storage.Storage;

public class StorageTest {

    @Test
    public void testInvalidStorageFilePathException() {
        try {
            Storage storage = new Storage("data", "hello");
            fail();
        } catch (Exception e) {
            assertEquals("Storage file should end with '.txt'", e.getMessage());
        }
    }
}
