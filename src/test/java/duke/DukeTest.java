package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.StorageStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {

    private static Duke duke;

    @BeforeEach
    public void setUpDuke() {
        duke = new Duke(new StorageStub());
    }

    @Test
    public void initialize_valid_success() {
        try {
            duke.initialize();
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void processUserInput_byeInput_sayGoodbye() {
        String result = duke.processUserInput("bye");
        Assertions.assertEquals("Bye! Hope to see you again!", result);
    }
}
