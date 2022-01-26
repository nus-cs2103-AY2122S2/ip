package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoTestString() {
        Todo todo = new Todo("Read book");

        assertEquals("[T][ ] Read book", todo.toString());
    }
}
