package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineToString_validInput_validOutput() {
        assertEquals("D|false|homework|Dec 10 2014|\n", new Deadline("homework", "2014-12-10").toString());
    }

    @Test
    public void deadlinePrintTask_validInput_validOutput() {
        assertEquals("[D][ ] exam (by Jan 24 2020)", new Deadline("exam", "2020-01-24").printTask());
    }
}
