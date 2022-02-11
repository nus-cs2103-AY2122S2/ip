package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.admin.TaskList;

public class TaskListTest {
    private TaskList test = new TaskList();

    @Test
    public void taskCountTest() {
        assertEquals(0, test.getNumberOfTasks(), "Failed");
    }
}
