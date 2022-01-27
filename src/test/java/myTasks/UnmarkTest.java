import myTasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkTest {
    @Test
    void toStringTest() {
        Todo t = new Todo("help mom");
        t.markAsDone();
        assertEquals("[T][X] help mom", t.toString());
        t.markAsNotDone();
        assertEquals("[T][ ] help mom", t.toString());
    }
}