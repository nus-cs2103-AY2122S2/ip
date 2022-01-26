package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo t = new Todo("read book");
        assertEquals("[T][ ] read book", t.toString());
    }
}
