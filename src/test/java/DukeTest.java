import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void setUpSaveSystemTest(){
        String str = System.getProperty("user.dir");
        Path p = Paths.get(str, "test", "test.json");
        new Duke("test/", "test.json");
        assertEquals(true, Files.exists(p));
    }
}
