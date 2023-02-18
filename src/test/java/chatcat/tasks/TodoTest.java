package chatcat.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void createTodo() {
        assertEquals("[T][ ] test", new Todo("test").toString());
    }

    @Test
    public void done() {
        Todo todo = new Todo("test");
        todo.setDone();
        assertEquals("[T][X] test", todo.toString());
    }

    @Test
    public void undone() {
        Todo todo = new Todo("test");
        todo.setDone();
        todo.setUnDone();
        assertEquals("[T][ ] test", todo.toString());
    }
}
