package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_ToDoObject_Success() {
        assertEquals("[T][ ] read a book", new ToDo("read a book").toString());
    }

    @Test
    public void markDoneToString_ToDoObject_Success() {
        ToDo todo = new ToDo("read a book");
        todo.markDone();
        assertEquals("[T][X] read a book", todo.toString());
    }
}