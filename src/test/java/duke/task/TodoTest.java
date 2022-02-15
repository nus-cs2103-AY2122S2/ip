package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_todo_correctStringRepresentationReturned() {
        Todo todo = new Todo("test", true);

        assertEquals(todo.toString(), "[T][X] test");
    }

    @Test
    public void toData_todo_correctDataRepresentationReturned() {
        Todo todo = new Todo("test", true);

        assertEquals(todo.toData(), "T | true | test");
    }
}
