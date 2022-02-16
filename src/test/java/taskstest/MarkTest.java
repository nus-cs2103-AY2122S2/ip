package taskstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mytasks.Todo;

public class MarkTest {
    @Test
    void toStringTest() {
        Todo t = new Todo("help mom");
        t.markAsDone();
        assertEquals("[T][X] help mom", t.toString());
    }
}
