package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void deadlineTask_valid_success() {
        assertTrue(new DeadlineTask("Title 1", "2022-01-01") instanceof DeadlineTask);
        assertTrue(new DeadlineTask("Title 2", "2022-01-01", "11:11") instanceof DeadlineTask);
        assertTrue(new DeadlineTask("Title 3", true, "2022-01-01") instanceof DeadlineTask);
        assertTrue(new DeadlineTask("Title 4", true, "2022-01-01", "11:11") instanceof DeadlineTask);
    }

    @Test
    public void deadlineTask_getByTime_success() {
        assertEquals(new DeadlineTask("Title 1", "2022-01-01").getByTime(),
                "(by: 2022-01-01)");
        assertEquals(new DeadlineTask("Title 2", "2022-01-01", "11:11").getByTime(),
                "(by: 2022-01-01 11:11)");
    }

    @Test
    public void deadlineTask_toOutputLine_success() {
        assertEquals(new DeadlineTask("Title 1", "2022-01-01").toOutputLine(),
                "D | 0 | Title 1 | 2022-01-01");
        assertEquals(new DeadlineTask("Title 2", "2022-01-01", "11:11").toOutputLine(),
                "D | 0 | Title 2 | 2022-01-01 | 11:11");
        assertEquals(new DeadlineTask("Title 3", true, "2022-01-01").toOutputLine(),
                "D | 1 | Title 3 | 2022-01-01");
        assertEquals(new DeadlineTask("Title 4", true, "2022-01-01", "11:11").toOutputLine(),
                "D | 1 | Title 4 | 2022-01-01 | 11:11");
    }

    @Test
    public void deadlineTask_toString_success() {
        assertEquals(new DeadlineTask("Title 1", "2022-01-01").toString(),
                "Title 1 (by: 2022-01-01)");
        assertEquals(new DeadlineTask("Title 2", "2022-01-01", "11:11").toString(),
                "Title 2 (by: 2022-01-01 11:11)");
    }
}
