import org.junit.jupiter.api.Test;

import saitama.tasks.Task;
import saitama.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    private Task task = new ToDo("Eat");

    @Test
    void testToString() {
        assertEquals("[T][ ] Eat", task.toString());
        task.markAsDone();
        assertEquals("[T][X] Eat", task.toString());
    }
}
