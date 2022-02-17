package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringResult() {
        assertEquals("[T][ ] go for lunch", new Todo("go for lunch").toString());
    }

}
