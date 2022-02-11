package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_expectString_expectedBehavior() {
        ToDo freshToDo = new ToDo("Try this");
        assertEquals("[T][ ] Try this", freshToDo.toString());
    }
}
