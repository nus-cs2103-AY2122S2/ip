import duke.system.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void UiTest() {
        assertEquals(new Ui().showGreeting(), "Hello! I'm Duke\nNice to meet you!");
    }
}
