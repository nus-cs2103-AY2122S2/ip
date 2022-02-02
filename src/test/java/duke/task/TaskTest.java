package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The TaskTest class tests the functionality of the Task class.
 *
 * @author Rdac0
 */
public class TaskTest {
    private Task task = new Task("test");

    /**
     * DummyTest, always passes.
     */
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    /**
     * Tests getName functionality.
     */
    @Test
    public void getName() {
        assertEquals("test", task.getName());
    }

    /**
     * Tests the functionality of getTime.
     * It shouldn't cause an error but will return null,
     * as Tasks don't have time.
     * Normally, Task.getTime() shouldn't be called.
     */
    @Test
    public void getTime_nullReturn() {
        assertEquals(null, task.getTime());
    }
}
