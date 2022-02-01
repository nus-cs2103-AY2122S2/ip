package heylo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_validTodo_success() {
        Todo todo = new Todo("todo-1");
        assertEquals(" [T][ ] todo-1", todo.toString());
    }
}