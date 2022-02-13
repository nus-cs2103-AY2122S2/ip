package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.commands.ByeCommand;
import seedu.commands.Command;
import seedu.commands.DeadlineCommand;
import seedu.duke.DukeException;
import seedu.duke.Parser;
import seedu.task.Todo;

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
        assertThrows(DukeException.class, () -> d.validate(""));
        assertThrows(DukeException.class, () -> d.validate("notAString"));
        assertThrows(DukeException.class, () -> d.validate("task /by notADate"));
        assertThrows(DukeException.class, () -> d.validate("task /by 1-2-2020 7pm"));
    }

    @Test
    public void eventTask() {
        Todo t = new Todo("1");
        assertEquals("[T][0][ ] 1", t.toString());
        assertEquals("T\t1\tfalse\t0", t.toFile());
    }

    @Test
    public void testDefaultExit() {
        ByeCommand b = new ByeCommand();
        assertFalse(Command.isExit());
        b.execute(null);
        assertTrue(Command.isExit());
    }
}
