package tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoString() {
        Todo todo = new Todo("test");
        String todoExpected = "[T][ ] test";
        assertEquals(todoExpected, todo.toString());
    }

    @Test
    public void testMarkedTodoString() {
        Todo todo = new Todo("test");
        String todoExpected = "[T][X] test";
        todo.markIsDone();
        assertEquals(todoExpected, todo.toString());
    }
}