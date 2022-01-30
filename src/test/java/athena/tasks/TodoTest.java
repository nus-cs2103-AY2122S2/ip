package athena.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    private static Todo markedTodo;
    private static Todo unmarkedTodo;

    @BeforeAll
    static void init() {
        unmarkedTodo = new Todo("grocery shopping");
        markedTodo = new Todo("birthday");
        markedTodo.setDone();
    }

    @Test
    void markAsDone() {
        Todo todo = new Todo("a");
        todo.setDone();
        assertEquals("[T][X] a", todo.toString());
    }

    @Test
    void markAsNotDone() {
        Todo todo = new Todo("a");
        todo.setDone();
        todo.setNotDone();
        assertEquals("[T][ ] a", todo.toString());
    }

    @Test
    void getSaveFormat_markedTodo() {
        assertEquals("T|1|birthday", markedTodo.getSaveFormat());
    }

    @Test
    void getSaveFormat_unmarkedTodo() {
        assertEquals("T|0|grocery shopping", unmarkedTodo.getSaveFormat());
    }

    @Test
    void testToString_markedTodo() {
        assertEquals("[T][X] birthday", markedTodo.toString());
    }

    @Test
    void testToString_unmarkedTodo() {
        assertEquals("[T][ ] grocery shopping", unmarkedTodo.toString());
    }

    @Test
    void getIcon() {
        assertEquals("T", markedTodo.getIcon());
    }
}