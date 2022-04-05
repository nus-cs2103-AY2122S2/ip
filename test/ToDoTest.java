import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringConversion_validToDo_success() throws Exception {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }

    @Test
    public void toTextConversion_validToDo_success() throws Exception {
        assertEquals("T | 0 | read book" + "\n", new ToDo("read book").toText());
    }

    @Test
    public void testMark() {
        ToDo testToDo = new ToDo("read book");
        testToDo.mark();
        assertEquals("[T][X] read book", testToDo.toString());
        assertEquals("T | 1 | read book" + "\n", testToDo.toText());
    }
}
