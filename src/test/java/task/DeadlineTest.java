package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exception.DukeException;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void symbolTest() throws DukeException {
        Deadline testDeadline = new Deadline("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testDeadline.symbol(), "D");
    }

    @Test
    public void displayTimeTest() throws DukeException {
        Deadline testDeadline = new Deadline("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testDeadline.displayTime(), "Complete 21003 ip tasks30 JANUARY 2021");
    }
}
