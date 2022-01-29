package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

    @Test
    void getCommand_validCommands_success() {
        try {
            assertEquals(CommandType.BYE, CommandType.getCommand("bye"));
            assertEquals(CommandType.LIST, CommandType.getCommand("list"));
            assertEquals(CommandType.MARK, CommandType.getCommand("mark"));
            assertEquals(CommandType.UNMARK, CommandType.getCommand("unmark"));
            assertEquals(CommandType.DELETE, CommandType.getCommand("delete"));
            assertEquals(CommandType.TODO, CommandType.getCommand("todo"));
            assertEquals(CommandType.EVENT, CommandType.getCommand("event"));
            assertEquals(CommandType.DEADLINE, CommandType.getCommand("deadline"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void getCommand_invalidCommand_exceptionThrown() {
        try {
            CommandType.getCommand("invalidCommand");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, you've input a command I don't recognize. Please try again.", e.getMessage());
        }
    }

}