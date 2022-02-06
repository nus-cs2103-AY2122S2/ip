import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import karen.Ui;

public class UiTest {
    protected Ui ui;

    @Test
    public void displayUserInput() {
        assertEquals(ui.displayUserInput("hello"), "hello");
    }

}
