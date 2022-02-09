package bernie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bernie.exceptions.InvalidArgumentException;

public class ToDoTest {
    @Test
    public void createToDoTest() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }

    @Test
    public void markMarkedToDoTest() {
        ToDo todo = new ToDo("borrow book");
        todo.markDone();
        try {
            todo.checkMark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot mark a task already done!", e.getMessage());
        }
    }

    @Test
    public void unmarkUnmarkedToDoTest() {
        ToDo todo = new ToDo("borrow book");
        todo.markNotDone();
        try {
            todo.checkUnmark();
        } catch (InvalidArgumentException e) {
            assertEquals("Cannot unmark a task not done!", e.getMessage());
        }
    }

    @Test
    public void markToDoTest() {
        ToDo todo = new ToDo("borrow book");
        todo.markDone();
        assertEquals("[T][X] borrow book", todo.toString());
    }

    @Test
    public void unmarkToDoTest() {
        ToDo todo = new ToDo("borrow book");
        todo.markDone();
        todo.markNotDone();
        assertEquals("[T][ ] borrow book", todo.toString());
    }
}
