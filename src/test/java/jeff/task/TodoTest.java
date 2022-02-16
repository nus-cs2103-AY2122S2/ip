package jeff.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testString() {
        String description = "testing";
        Todo currTask = new Todo(description);
        assertEquals("[T][ ] testing", currTask.toString());
    }
}
