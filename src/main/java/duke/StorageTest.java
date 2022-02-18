package duke;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

class StorageTest {
    @Test
    public void correctFile() {
        String file = "data/duke.txt";
    assertEquals("data/duke.txt", file);
    }
}