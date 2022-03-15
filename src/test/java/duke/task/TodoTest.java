package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_unmarked_noxShown() {
        Todo todo = new Todo("hi");
        assertEquals("[T][ ] hi", todo.toString());
    }

    @Test
    public void toString_marked_xShown() {
        Todo todo = new Todo("hello there!");
        todo.mark();
        assertEquals("[T][X] hello there!", todo.toString());
    }
}
