package apollo.commands;

import static apollo.messages.Messages.EXIT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;

public class ExitCommandTest {
    @Test
    void execute() throws ApolloOutOfBoundsException {
        Command exit = new ExitCommand();
        assertEquals(EXIT_MESSAGE, exit.execute());
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
