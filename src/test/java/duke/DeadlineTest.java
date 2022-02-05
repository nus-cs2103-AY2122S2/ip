package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void deadlineTestOne() {
        Deadline deadline = new Deadline("test1", false, "D", "2022-12-01 12:20");
        assertEquals("[D][ ] test1 (by: Dec 1 2022, 12:20)", deadline.toString());
    }

    @Test
    public void deadlineTestTwo() {
        Deadline deadline = new Deadline("test2", true, "D", "2022-12-01 12:20");
        assertEquals("[D][X] test2 (by: Dec 1 2022, 12:20)", deadline.toString());
    }

}
