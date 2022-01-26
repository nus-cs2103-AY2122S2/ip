package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {
    @Test
    public void uiTestWelcome_valid_success() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        UI ui = new UI();
        ui.showWelcome();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n     What can I do for you?\n" +
                "    ____________________________________________________________\n\n", lines);
    }

    @Test
    public void uiTestBye_valid_success() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        UI ui = new UI();
        ui.showExitMessage();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n\n", lines);
    }

    @Test
    public void uiTestPrint_valid_success() throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        UI ui = new UI();
        ui.printContent("This is a test line to fit within the top and bottom borders");
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n" +
                "     This is a test line to fit within the top and bottom borders\n" +
                "    ____________________________________________________________\n\n", lines);
    }
}
