package duke.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Duke;

public class DukeTest {
    @Test
    public void runDuke_run() {
        Duke duke = new Duke();
        assertTrue(duke.isRunning());
    }
}
