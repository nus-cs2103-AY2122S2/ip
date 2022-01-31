package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void uiTestWelcome_valid_success() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui ui = new Ui();
        ui.showWelcome();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n"
                + "     Hello! I'm Dusk\n     What can I do for you?\n"
                + "    ____________________________________________________________\n\n", lines);
    }

    @Test
    public void uiTestBye_valid_success() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui ui = new Ui();
        ui.showExitMessage();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n\n", lines);
    }

    @Test
    public void uiTestPrint_valid_success() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui ui = new Ui();
        ui.printContent("This is a test line to fit within the top and bottom borders");
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n"
                + "     This is a test line to fit within the top and bottom borders\n"
                + "    ____________________________________________________________\n\n", lines);
    }
}
