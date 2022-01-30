import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Task.Deadline;

public class DeadlineTest {
    @Test
    public void test_deadlineFormat() {
        assertEquals("D | 1 | week3_project | 2022-01-12",
                new Deadline("week3_project", "2022-01-12", true ).dataFormatOfTask());
    }
}
