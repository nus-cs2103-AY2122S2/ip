package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;

public class ExitCommandTest {
    @Test
    public void exit() {
        assertEquals(new ExitCommand().isExit(), true);
    }
}
