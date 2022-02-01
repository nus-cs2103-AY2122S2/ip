package jose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getSizeTest() {
        assertEquals(0, new TaskList().getSize());
    }

    @Test
    public void getTasksTest() {
        assertEquals(new ArrayList<>(), new TaskList().getTasks());
    }
}
