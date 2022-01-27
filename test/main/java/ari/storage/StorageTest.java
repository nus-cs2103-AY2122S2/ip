package main.java.ari.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private static final String NON_EXISTENT_FILE_NAME = "test/data/doesNotExistInitially.txt";

    @Test
    void setFile() {
        new Storage().setFile(NON_EXISTENT_FILE_NAME);
        assertEquals(true, Paths.get(NON_EXISTENT_FILE_NAME));
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}