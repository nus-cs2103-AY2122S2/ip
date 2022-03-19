package siri;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] run 2.4km", new Todo("run 2.4km", "T").toString());
    }
}
