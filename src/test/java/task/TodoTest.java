package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][X] the task todo", new Todo("the task todo", true).toString());
    }
}
