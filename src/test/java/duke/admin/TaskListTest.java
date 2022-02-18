package duke.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList test = new TaskList();

    /**
     * Tests the getNumberOfTasks method in Task list.
     */
    @Test
    public void taskCountTest() {
        assertEquals(0, test.getNumberOfTasks(), "Failed");
    }
}
