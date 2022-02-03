package command;

import karen.KarenException;
import karen.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCommandTest {
    @Test
    public void constructorOverload() {
        assertEquals("I don't understand anything - I want to speak with your manager",
                new InvalidCommand().getMessage());
        assertEquals("Override message",
                new InvalidCommand("Override message").getMessage());
    }

    @Test
    public void executeCommand() {
        try {
            InvalidCommand cmd = new InvalidCommand();
            cmd.execute();
        } catch (KarenException err) {
            assertEquals("I don't understand anything - I want to speak with your manager",
                    err.toString());
        }
    }

}
