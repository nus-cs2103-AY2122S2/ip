package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import echo.task.TodoTask;

public class TodoTaskTest {

    @Test
    @DisplayName("Should return expected save format")
    public void saveFormat_validTodoTask_expectedSaveFormat() {
        String desc = "read book";
        TodoTask todoTask = new TodoTask(desc);
        assertEquals("T | 0 | read book", todoTask.saveFormat());
    }

    @Test
    @DisplayName("Should return expected toString format")
    public void toString_validTodoTask_expectedToString() {
        String desc = "read book";
        TodoTask todoTask = new TodoTask(desc);
        assertEquals("[T][ ] read book", todoTask.toString());
    }

}
