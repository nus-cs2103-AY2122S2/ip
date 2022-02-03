import seedu.duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][X] do this thing",
                new ToDo("do this thing", true).toString());
    }
}
