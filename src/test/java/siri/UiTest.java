package siri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;





public class UiTest {

    /**
     * Test exit function of ui.
     */
    @Test
    public void exitTest() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        String expectedOutput = "Bye!! Hope to see you again soon!!\n";
        Ui ui = new Ui("Test");
        ui.exit();
        assertEquals(expectedOutput, ui.exit());
    }

}
