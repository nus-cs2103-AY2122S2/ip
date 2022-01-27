package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void testByeCommand() {
        assertTrue(new ByeCommand().isExit());
    }
}
