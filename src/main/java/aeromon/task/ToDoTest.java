package aeromon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_normal_success() {
        ToDo toDo = new ToDo("Outing");
        assertEquals("[T][ ] Outing", toDo.toString());
    }

    @Test
    public void toString_marked_success() {
        ToDo toDo = new ToDo("Outing");
        toDo.markAsDone();
        assertEquals("[T][X] Outing", toDo.toString());
    }

    @Test
    public void toOutputFormat_normal_success() {
        ToDo toDo = new ToDo("Outing");
        assertEquals("T / 0 / Outing", toDo.toOutputFormat());
    }

    @Test
    public void toOutputFormat_marked_indicated() {
        ToDo toDo = new ToDo("Outing");
        toDo.markAsDone();
        assertEquals("T / 1 / Outing", toDo.toOutputFormat());
    }
}
