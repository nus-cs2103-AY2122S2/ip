import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import duke.command.UnmarkCommand;

public class UnmarkCommandTest {

    @Test
    public void unmarkCommand_construct_notNull() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertNotNull(unmarkCommand);
    }

    @Test
    public void unmarkCommand_normal_isExitFalse() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertEquals(false, unmarkCommand.isExit());
    }

}
