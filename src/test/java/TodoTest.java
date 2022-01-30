import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Todo;

public class TodoTest {
    @Test
    public void createTodo_correctDescription_success() {
        assertEquals("[T][ ] make bread", (new Todo("make bread")).toString());
    }
}
