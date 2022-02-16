import duke.tasks.DeadlineTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTaskTest {
    @Test
    public void DeadlineTaskTest() {
        DeadlineTask task = new DeadlineTask("Math homework", "16022022", "1200");
        assertEquals(task.getPrefix(), "D");
        assertEquals(task.getDate(), "16022022");
        assertEquals(task.getTime(), "1200");
    }
}
