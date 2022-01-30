package chatbots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.SaveFileModifiedException;

/**
 * This class encapsulates the tests done on Fluffers (in particular, the factory method)
 *
 * @author Ong Han Yang
 */
public class FluffersTest {
    /**
     * Tests the Fluffers factory method with the correct file.
     *
     * @throws SaveFileModifiedException never.
     */
    @Test
    public void getTest_goodFile_success() throws SaveFileModifiedException {
        assertEquals("Version 0.2, Last Updated: 30 Jan 2022",
                Fluffers.get("data", "saved-tasklist.txt").toString());
    }

    /**
     * Tests the Fluffers factory method with an entirely unreadable file.
     */
    @Test
    public void getTest_modifiedTaskFile_exceptionThrown() {
        try {
            assertEquals("Version 0.2, Last Updated: 30 Jan 2022",
                    Fluffers.get("data", "bad-tasklist.txt").toString());
            fail();
        } catch (SaveFileModifiedException e) {
            assertEquals("Unknown task type detected!", e.getMessage());
        }
    }

    /**
     * Tests the Fluffers factory method with a modified task state for the 2 tasks.
     */
    @Test
    public void getTest_modifiedTaskState_exceptionThrown() {
        try {
            assertEquals("Version 0.2, Last Updated: 30 Jan 2022",
                    Fluffers.get("data", "modified-tasklist.txt").toString());
            fail();
        } catch (SaveFileModifiedException e) {
            assertEquals("Unknown task state detected!", e.getMessage());
        }
    }

}
