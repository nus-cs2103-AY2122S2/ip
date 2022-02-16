package taskstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mytasks.Todo;

public class TodoTest {
    @Test
    void toStringTest() {
        Todo t = new Todo("help mom");
        assertEquals("[T][ ] help mom", t.toString());
    }
}
