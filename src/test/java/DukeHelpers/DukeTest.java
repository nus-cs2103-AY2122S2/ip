package DukeHelpers;

import Commands.Command;
import Exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Ui ui;

    @Test
    public void testWelcome() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
        ui.welcome();
        assertEquals("Hello! I'm Duke\n" + "What can I do for you?",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWrongDate() {
        String s = "OOPS!!! Time of deadline is missing. Please indicate a stipulated time.";
        try {
            Parser.isCommand("deadline cook /by", Command.DEADLINE);
        } catch (DukeException e) {
            assertEquals(s, e.getMessage());
        }
    }

}
