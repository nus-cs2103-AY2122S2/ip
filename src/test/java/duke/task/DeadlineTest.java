package duke.task;

import main.java.duke.main.DukeException;
import main.java.duke.task.Task;
import main.java.duke.task.TaskType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void correctInfoTable() {

        String testCase = "deadline test /by 2022-03-04";
        HashMap<String, Object> expectedTable = new HashMap<>();
        expectedTable.put("task_type", TaskType.DEADLINE);
        expectedTable.put("description", "test");
        expectedTable.put("due_time", LocalDate.parse("2022-03-04"));
        expectedTable.put("is_done", false);

        try {
            Task deadline = Task.of(testCase);
            assertEquals(expectedTable, deadline.getInfoTable(), "Failed: deadline loads incorrectly.");

        } catch (DukeException e) {
            fail("Throws an unexpected exception: " + e.getMessage());
        }

    }
}