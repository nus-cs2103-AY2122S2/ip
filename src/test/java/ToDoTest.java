import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] coding", new ToDo("coding").toString());
    }

    @Test
    public void testToStringForStorage() {
        assertEquals("T | 0 | coding", new ToDo("coding").toStringForStorage());
    }
}
