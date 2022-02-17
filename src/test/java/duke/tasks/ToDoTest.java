package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidOperationException;


public class ToDoTest {
    private ToDo todo;

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] this", new ToDo("this").toString());
    }

    @Test
    public void toDo_markDone_success() throws InvalidOperationException {
        todo = new ToDo("this");
        todo.mark();
        assertEquals("[T][X] this", todo.toString());
    }

    @Test
    public void toDo_markDone_exceptionThrown() throws InvalidOperationException {
        try {
            todo = new ToDo("this");
            todo.mark();
            assertEquals("[T][X] this", todo.toString());
            todo.mark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already marked", e.toString());
        }
    }

    @Test
    public void toDo_unmark_success() throws InvalidOperationException {
        todo = new ToDo("this");
        todo.mark();
        assertEquals("[T][X] this", todo.toString());
        todo.unmark();
        assertEquals("[T][ ] this", todo.toString());
    }

    @Test
    public void toDo_unmark_exceptionThrown() throws InvalidOperationException {
        try {
            todo = new ToDo("this");
            todo.unmark();
        } catch (InvalidOperationException e) {
            assertEquals("This task is already unmarked", e.toString());
        }
    }
}
