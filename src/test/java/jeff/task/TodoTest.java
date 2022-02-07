package jeff.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testString() {
        String description = "testing";
        Todo currTask = new Todo(description);
        assertEquals("[T][ ] testing", currTask.toString());
    }
}
