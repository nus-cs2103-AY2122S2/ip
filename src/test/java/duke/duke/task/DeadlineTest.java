package duke.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.dukeexceptions.DukeException;
import duke.task.Deadline;

public class DeadlineTest {
    @Test
    void test_toStore() throws DukeException {
        assertEquals("D | 0 | name | 2/12/2019 1800",
                new Deadline("name", "2/12/2019 1800").toStore());
    }
    @Test
    void test_display() throws DukeException {
        assertEquals("[D] [ ] name (by Dec 2 2019 6:00PM)",
                new Deadline("name", "2/12/2019 1800").display());
    }
}
