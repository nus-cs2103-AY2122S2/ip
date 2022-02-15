package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todoTestString() {
        Todo todo = new Todo("Read book");

        assertEquals("[T][ ] Read book", todo.toString());
    }
}
