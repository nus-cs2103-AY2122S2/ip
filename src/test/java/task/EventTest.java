package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exception.DukeException;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void symbolTest() throws DukeException {
        Event testEvent = new Event("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testEvent.symbol(), "E");
    }

    @Test
    public void displayTimeTest() throws DukeException {
        Event testEvent = new Event("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testEvent.displayTime(), "Complete 21003 ip tasks30 JANUARY 2021");
    }
}
