package heylo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_validTodo_success() {
        Todo todo = new Todo("todo-1");
        assertEquals(" [T][ ] todo-1", todo.toString());
    }
}
