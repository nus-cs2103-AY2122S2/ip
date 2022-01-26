package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("test");

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void getName() {
        assertEquals("test", task.getName());
    }

    @Test
    public void getTime_nullReturn() {
        assertEquals(null, task.getTime());
    }
}
