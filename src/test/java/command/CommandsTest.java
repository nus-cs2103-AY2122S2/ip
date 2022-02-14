package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import duke.command.BingChillingCommand;

public class CommandsTest {

    @Test
    public void testCommandTypeMapping() {
        HashMap<String, String> h = BingChillingCommand.getTaskTypeMap();
        assertEquals("ADD_COMMAND", h.get("todo"));
        assertEquals("ADD_COMMAND", h.get("event"));
        assertEquals("ADD_COMMAND", h.get("deadline"));
        assertEquals("DELETE_COMMAND", h.get("delete"));
        assertEquals("MARK_COMMAND", h.get("mark"));
        assertEquals("UNMARK_COMMAND", h.get("unmark"));
        assertEquals("OUTPUT_COMMAND", h.get("list"));
        assertEquals("POSTPONE_COMMAND", h.get("postpone"));
        assertEquals("CLEAR_COMMAND", h.get("clear"));
    }
}
