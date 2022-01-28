package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void todoToString() {
        Todo todo = new Todo("New todo");
        Assertions.assertEquals("[T][ ] New todo ", todo.toString());
    }
}
