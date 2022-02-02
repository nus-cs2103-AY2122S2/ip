package kenobi.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void taskException_emptyTaskName_exceptionThrown() {
        try {
            new ToDo("");
            fail();
        } catch (TaskException e) {
            assertEquals("Task name cannot be empty", e.getMessage());
        }
    }
}
1