package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCommandTest {
    @Test
    void execute() throws ApolloOutOfBoundsException {
        Command invalid = new InvalidCommand();
        String expected = "Apologies, I do not understand that. ";
        assertEquals(expected, invalid.execute());
    }
}
