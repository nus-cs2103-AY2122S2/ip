package duke;

import org.junit.jupiter.api.Test;

import seedu.commands.ByeCommand;
import seedu.commands.DeadlineCommand;
import seedu.duke.DukeException;
import seedu.duke.Parser;
import seedu.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    @Test
    public void parseTest() {
        Parser p = new Parser();
        assertThrows(DukeException.class, () -> p.parse(""));
        assertThrows(DukeException.class, () -> p.parse("notCommand"));
    }

    @Test
    public void parseDeadline() {
        DeadlineCommand d = new DeadlineCommand();
        assertThrows(DukeException.class, () -> d.input(""));
        assertThrows(DukeException.class, () -> d.input("notAString"));
        assertThrows(DukeException.class, () -> d.input("task /by notADate"));
        assertThrows(DukeException.class, () -> d.input("task /by 1-2-2020 7pm"));
    }

    @Test
    public void eventTask() {
        Todo t = new Todo("1");
        assertEquals("[T][ ] 1", t.toString());
        assertEquals("T\t1\tfalse", t.toFile());
    }

    @Test
    public void testDefaultExit() {
        ByeCommand b = new ByeCommand();
        assertFalse(b.isExit());
        b.execute(null, null, null);
        assertTrue(b.isExit());
    }
}
