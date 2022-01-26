package duke.test;

import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void runningTest(){
        Duke duke = new Duke();
        assertTrue(duke.isRunning());
    }
}