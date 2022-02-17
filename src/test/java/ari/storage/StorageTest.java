package ari.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class StorageTest {
    private static final String NON_EXISTENT_FILE_NAME = "src/test/data/doesNotExistInitially.txt";

    @Test
    void setFile() {
        new Storage().setFile(NON_EXISTENT_FILE_NAME);
        assertEquals(Path.of(NON_EXISTENT_FILE_NAME), Paths.get(NON_EXISTENT_FILE_NAME));
        try {
            Files.delete(Paths.get(NON_EXISTENT_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}
