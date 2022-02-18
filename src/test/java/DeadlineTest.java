import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.dukeexceptions.DeadlineException;
import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void test_deadlineFormat() throws DeadlineException {
        assertEquals("D | 1 | week3_project | 2022-01-12",
                new Deadline("week3_project", "2022-01-12", true).dataFormatOfTask());
    }
}
