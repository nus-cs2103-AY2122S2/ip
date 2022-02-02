package duke;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void UiAskForCommandTest_success() {
        String expected = "What is your command: \n";
        String output = ui.createWelcomeMessage();
        assertEquals(expected, output);
    }
}
