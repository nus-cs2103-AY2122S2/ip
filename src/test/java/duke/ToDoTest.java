package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToString() {
        assertEquals("[T][X] return book", new ToDo(" return book", true).toString());
    }

}
