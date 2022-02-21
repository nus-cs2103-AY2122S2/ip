package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TodoTest class, containing tests for the Todo class.
 *
 * @author Jet Tan
 */
public class TodoTest {
    @Test
    public void testStringOutput_desc() {
        Todo todo = new Todo("aaaa");
        assertEquals("[T][ ] aaaa", todo.toString());
    }
}