package command;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void exit() {
        assertEquals(new ExitCommand().isExit(), true);
    }
}
