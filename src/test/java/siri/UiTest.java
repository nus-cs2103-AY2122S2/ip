package siri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

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
