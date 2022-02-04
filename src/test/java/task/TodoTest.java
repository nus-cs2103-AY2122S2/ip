package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void ToDo_saveData_success() {
        ToDo todo = new ToDo("ABC");
        assertEquals("T | 0 | ABC", todo.saveData());
    }
}
