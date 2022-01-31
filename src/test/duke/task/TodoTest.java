package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][X] the task todo", new Todo("the task todo", true).toString());
    }
}