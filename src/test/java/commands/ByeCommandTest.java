package commands;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.ByeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test Bye command
 */
public class ByeCommandTest {
    private ByeCommand cmd;

    /**
     * Method executed to set-up before each test
     * ensures command contains a new bye command
     */
    @BeforeEach
    void setUp() {
        cmd = new ByeCommand();
    }

    /**
     * Tests command does not store task list across commands
     */
    @Test
    void getListBeforeExecute() {
        assertNull(cmd.getList());
    }

    /**
     * Tests command returns correct boolean value
     * for checking if program terminates
     */
    @Test
    void endsProgram() {
        assertTrue(cmd.endsProgram());
    }
}
