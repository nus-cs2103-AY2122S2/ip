package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void dummyTodo() {
        Todo todo = new Todo("Test Todo");

        assertEquals("Test Todo", todo.getDescription());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Test Todo");

        assertEquals("[T][ ] Test Todo", todo.toString());
    }
}
