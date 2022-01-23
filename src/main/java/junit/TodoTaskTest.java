package junit;

import echo.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void testSaveFormat() {
        String desc = "read book";
        TodoTask todoTask = new TodoTask(desc);
        assertEquals("T | 0 | read book", todoTask.saveFormat());
    }

    @Test
    public void testToString() {
        String desc = "read book";
        TodoTask todoTask = new TodoTask(desc);
        assertEquals("[T][ ] read book", todoTask.toString());
    }

}
