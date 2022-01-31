import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void testdataFormatOfTask1() {
        assertEquals("T | 0 | doing project",
                new Todo("doing project").dataFormatOfTask());
    }

    @Test
    public void testdataFormatOfTask2() {
        assertEquals("T | 1 | doing project",
                new Todo("doing project", true).dataFormatOfTask());
    }
}
