package duke;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    @Test
    public void UiTest() {

        Ui ui = new Ui();

        String bye = ui.finalBye();
        String loadingError = ui.showLoadingError();

        assertEquals("Bye. Hope to see you again soon!",bye);
        assertEquals("System data could not be loaded!", loadingError);
    }
}
