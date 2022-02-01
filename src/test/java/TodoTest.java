package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    public void toStringTest() {
        Todo todo = new Todo("Wash clothes");

        assertEquals("[T][ ] Wash Clothes", todo.toString());
    }
}
