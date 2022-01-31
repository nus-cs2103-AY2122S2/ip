package commands;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    private ByeCommand cmd;

    @BeforeEach
    void setUp() {
        cmd = new ByeCommand();
    }

    @Test
    void getListBeforeExecute() {
        assertNull(cmd.getList());
    }

    @Test
    void endsProgram() {
        assertTrue(cmd.endsProgram());
    }
}
