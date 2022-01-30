package duke.task;

import main.java.duke.main.DukeException;
import main.java.duke.task.Task;
import main.java.duke.task.TaskType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void correctInfoTable() {

        String testCase = "event test /at 3pm-5pm";
        HashMap<String, Object> expectedTable = new HashMap<>();
        expectedTable.put("task_type", TaskType.EVENT);
        expectedTable.put("description", "test");
        expectedTable.put("time_range", "3pm-5pm");
        expectedTable.put("is_done", false);

        try {
            Task deadline = Task.of(testCase);
            assertEquals(expectedTable, deadline.getInfoTable(), "Failed: event loads incorrectly.");

        } catch (DukeException e) {
            fail("Throws an unexpected exception: " + e.getMessage());
        }

    }
}