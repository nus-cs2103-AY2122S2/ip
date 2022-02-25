import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoDescriptionTest() {
        Todo todo = new Todo("Arts Bonding Camp");
        assertEquals(todo.toString(), "[T][ ] Arts Bonding Camp");
    }
}