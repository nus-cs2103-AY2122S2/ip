package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TaskTypeTest {

    @Test
    public void testMatching_oneToThree_success() {
        assertEquals(TaskType.TODO, TaskType.matchType(1));
        assertEquals(TaskType.DEADLINE, TaskType.matchType(2));
        assertEquals(TaskType.EVENT, TaskType.matchType(3));
    }

    @Test
    public void testMatching_four_null() {
        assertNull(TaskType.matchType(4));
    }
}
