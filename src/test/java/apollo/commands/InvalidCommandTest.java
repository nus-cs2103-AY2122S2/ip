package apollo.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import apollo.exceptions.ApolloOutOfBoundsException;

public class InvalidCommandTest {
    @Test
    void execute() throws ApolloOutOfBoundsException {
        Command invalid = new InvalidCommand();
        String expected = "Apologies, I do not understand that. ";
        assertEquals(expected, invalid.execute());
    }
}
