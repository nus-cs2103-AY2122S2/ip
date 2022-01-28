import duke.info.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void ConstructorTest() {
        Todo testTodo = new Todo("Finish CS2103T Assignment", false);
        assertEquals(testTodo.getAction(), "Finish CS2103T Assignment");
        assertEquals(testTodo.getType(), "todo");
    }

    @Test
    public void StringTest() {
        Todo testTodo = new Todo("Finish CS2103T Assignment", false);
        assertEquals(testTodo.toString(), "[T][ ] Finish CS2103T Assignment");
    }
}
