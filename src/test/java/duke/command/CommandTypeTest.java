package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTypeTest {
    @Test
    public void fromString_validCommand_success() {
        assertEquals(CommandType.fromString("todo"), CommandType.ADD_TODO);
        assertEquals(CommandType.fromString("deadline"), CommandType.ADD_DEADLINE);
        assertEquals(CommandType.fromString("event"), CommandType.ADD_EVENT);
        assertEquals(CommandType.fromString("list"), CommandType.LIST);
        assertEquals(CommandType.fromString("mark"), CommandType.MARK_TASK);
        assertEquals(CommandType.fromString("unmark"), CommandType.UNMARK_TASK);
        assertEquals(CommandType.fromString("delete"), CommandType.DELETE_TASK);
        assertEquals(CommandType.fromString("bye"), CommandType.EXIT);
    }

    @Test
    public void fromString_unknownCommand_exceptionThrown() {
        assertThrows(DukeException.class, () -> CommandType.fromString("unknownCommand"));
    }
}
