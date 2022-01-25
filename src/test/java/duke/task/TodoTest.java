package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testTodoMark() {
        Todo todo = new Todo("read book");
        todo.markTask();
        assertEquals("[T][X] read book", todo.toString());
    }
}
