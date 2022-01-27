package mickey.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todoTest() {
        assertEquals("[T][ ] test", new ToDo("test").toString());
    }
}
