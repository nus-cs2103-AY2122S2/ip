package duke;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void successfulTodoTest() {
        assertEquals("[T][ ] wassup bro", new Todo("wassup bro").toString());
    }

    @Test
    public void invalidTodoTest() {
        try {
            Parser.parse("todo");
        } catch(DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

}