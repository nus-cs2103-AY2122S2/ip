package alfred.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void todo_valid_toString() {
        ToDo todo = new ToDo("Expected description");
        assertEquals(todo.toString(), "[T][ ] Expected description");
    }

}
