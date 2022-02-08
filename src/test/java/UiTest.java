import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import karen.Ui;

public class UiTest {
    protected Ui ui;

    @BeforeEach
    public void setUpUi() {
        this.ui = new Ui();
    }

    @Test
    public void displayUserInput() {
        assertEquals(ui.displayUserInput("hello"), "hello");
    }
    @AfterEach
    public void tearDown() {
        this.ui = null;
    }
}
