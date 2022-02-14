package duke.task;

import duke.task.tasks.Deadline;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    public static Deadline deadlineNotDone;

    @BeforeAll
    public static void initializeTest() {
        deadlineNotDone = new Deadline("eat", "2019-12-12");
    }

    @Test
    public void switchMark_markTask_success() {
        deadlineNotDone.switchMark("mark");
        assertTrue(deadlineNotDone.isDone());
    }

    @Test
    public void getStatusIcon_markedTask_success() {
        String icon = deadlineNotDone.getStatusIcon();
        assertEquals(icon, "X");
    }

    @Test
    public void switchMark_unmarkTask_success() {
        deadlineNotDone.switchMark("unmark");
        assertFalse(deadlineNotDone.isDone());
    }

    @Test
    public void getStatusIcon_unmarkedTask_success() {
        String icon = deadlineNotDone.getStatusIcon();
        assertEquals(icon, " ");
    }
}
