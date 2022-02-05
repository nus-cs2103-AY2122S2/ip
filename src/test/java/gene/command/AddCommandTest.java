package gene.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    void isExitTest() {
        Assertions.assertEquals(false, new AddCommand("", "").isExit());
    }
}
