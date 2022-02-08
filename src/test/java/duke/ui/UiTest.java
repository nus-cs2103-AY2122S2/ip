package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {
    /*
    Java code normally use camelCase for method names e.g., testStringConversion
    but when writing test methods, sometimes another convention is used:
    whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    e.g., intDivision_zeroDivisor_exceptionThrown

    Tests use Assert.assertEquals(expected, actual) methods to compare the expected output with the actual output.
     */

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void drawDivider_drawDividerViaSystemOut_success() {
        setUpStreams();
        Ui.drawDivider();
        assertEquals("________________________________________\n", outContent.toString());
        restoreStreams();
    }

    @Test
    public void endProgram_endProgramMessageViaSystemOut_success() {
        setUpStreams();
        Ui ui = new Ui();
        ui.endProgram();
        assertEquals("________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "________________________________________\n",
                outContent.toString());
        restoreStreams();
    }
}
