package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    @Test
    void execute() throws ApolloOutOfBoundsException {
        Command exit = new ExitCommand();
        String expected = "See you next time. \nI am always available when you need me. ";
        assertEquals(expected, exit.execute());
    }

    @Test
    void isExit() {
        Command exit = new ExitCommand();
        Command add = new AddCommand("", null, Task.Type.TODO);
        Command delete = new DeleteCommand(0);
        Command invalid = new InvalidCommand();
        Command list = new ListCommand();
        Command mark = new MarkCommand(0, true);
        assertTrue(ExitCommand.isExit(exit));
        assertFalse(ExitCommand.isExit(add));
        assertFalse(ExitCommand.isExit(delete));
        assertFalse(ExitCommand.isExit(invalid));
        assertFalse(ExitCommand.isExit(list));
        assertFalse(ExitCommand.isExit(mark));
    }
}
