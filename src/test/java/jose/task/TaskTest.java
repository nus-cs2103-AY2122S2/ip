package jose.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getStatusIconTest_marked() {
        assertEquals("[X]", new Task("", true, Task.Priority.LOW).getStatusIcon());
    }

    @Test
    public void getStatusIconTest_unmarked() {
        assertEquals("[ ]", new Task("", false, Task.Priority.LOW).getStatusIcon());
    }

    @Test
    public void formatDataTest() {
        assertEquals("1|0|test", new Task("test").formatData());
    }

    @Test
    public void toStringTest() {
        assertEquals("[1][ ] test", new Task("test").toString());
    }
}
