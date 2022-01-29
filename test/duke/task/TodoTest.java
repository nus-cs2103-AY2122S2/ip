package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testStringConversion() {
        Todo todo = new Todo("new todo task", false);
        assertEquals("[T][ ] new todo task", todo.toString());
    }
}