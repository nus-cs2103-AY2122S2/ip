package aeromon.command;

import aeromon.AeromonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandManagerTest {

    @Test
    public void read_invalidCommand_exceptionThrown() {
        try {
            CommandManager.read("wrong");
        } catch (AeromonException e) {
            assertEquals("Nani? Me no understand what you say .-.", e.getMessage());
        }
    }

    @Test
    public void checkDescription_invalidFormat_exceptionThrown() {
        try {
            CommandManager.checkDescription("Deadline", "Outing");
        } catch (AeromonException e) {
            assertEquals("I need a new date with the correct format please :/", e.getMessage());
        }
    }
}
