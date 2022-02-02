package command;
import duke.command.DukeCommand;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsTest {

    @Test
    public void testCommandTypeMapping() {
        HashMap<String, String> h = DukeCommand.getTaskTypeMap();
        assertEquals("ADD_COMMAND", h.get("todo"));
        assertEquals("ADD_COMMAND", h.get("event"));
        assertEquals("ADD_COMMAND", h.get("deadline"));
        assertEquals("DELETE_COMMAND",h.get("delete"));
        assertEquals("MARK_COMMAND",h.get("mark"));
        assertEquals("UNMARK_COMMAND",h.get("unmark"));
        assertEquals("OUTPUT_COMMAND",h.get("list"));

    }
}
