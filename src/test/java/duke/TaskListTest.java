package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    TaskList test = new TaskList();

    @Test
    public void taskCountTest() {
        assertEquals(0, test.getNumberOfTasks(), "Failed");
    }
}
