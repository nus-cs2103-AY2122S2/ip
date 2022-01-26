package istjbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void testTodoToTxtString() {
        Todo todo = new Todo("test");
        assertEquals("todo / 0 / test", todo.toTxtString());
    }
}