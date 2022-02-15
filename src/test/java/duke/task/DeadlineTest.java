package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.util.DukeException;
import duke.util.Parser;

public class DeadlineTest {
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[D][ ] submit assignment (by: 29 Jan 2022, 2:00 PM)",
                new Deadline("submit assignment", Parser.parseDateTime("2022-01-29 14:00")).toString());
    }

    @Test
    public void getType() throws DukeException {
        assertEquals("D",
                new Deadline("submit assignment", Parser.parseDateTime("2022-01-29 14:00")).getType());
    }
}
