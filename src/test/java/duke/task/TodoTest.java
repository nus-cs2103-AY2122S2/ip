package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todo_saveData_success() {
        ToDo todo = new ToDo("ABC");
        assertEquals("T | 0 | NORM | ABC", todo.saveData());
    }
}
