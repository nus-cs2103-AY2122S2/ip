package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class StorageTest {
    @Test
    public void correctFile() {
        String file = "data/duke.txt";
    assertEquals("data/duke.txt", file);
    }
}