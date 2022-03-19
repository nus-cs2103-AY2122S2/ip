package duke.task;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TodoTest uses an assert, to verify correct values of Todo creation.
 */
public class TodoTest {
    @Test
    public void testStringResult() {
        assertEquals("[T][ ] go for lunch", new ToDos("go for lunch").toString());
    }

}
