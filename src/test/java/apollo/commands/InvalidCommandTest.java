package apollo.commands;

import static apollo.messages.Messages.INVALID_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import apollo.exceptions.ApolloOutOfBoundsException;

public class InvalidCommandTest {
    @Test
    void execute() throws ApolloOutOfBoundsException {
        Command invalid = new InvalidCommand();
        assertEquals(INVALID_COMMAND, invalid.execute());
    }
}
