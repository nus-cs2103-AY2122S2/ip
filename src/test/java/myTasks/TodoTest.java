import myTasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void toStringTest() {
        Todo t = new Todo("help mom");
        assertEquals("[T][ ] help mom", t.toString());
    }
}