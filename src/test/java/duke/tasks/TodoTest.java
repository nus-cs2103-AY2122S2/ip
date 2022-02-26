import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TodoTest {
    @Test
    public void todoDescriptionTest() {
        Todo todo = new Todo("Arts Bonding Camp");
        assertEquals(todo.toString(), "[T][ ] Arts Bonding Camp");
    }
}
