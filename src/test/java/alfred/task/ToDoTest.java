package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Encapsulates tests for ToDo class.
 */
public class ToDoTest {

    @Test
    public void todo_valid_toString() {
        ToDo todo = new ToDo("Expected description");
        assertEquals(todo.toString(), "[T][ ] Expected description");
    }

}
