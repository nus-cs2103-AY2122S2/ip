package duke.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class DukeTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;

    @Test
    public void uiWelcome_welcomePrinted() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
        ui.welcome();
        assertEquals("Hello! I'm duke.Duke\n" + "What can I do for you?",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void parserIsCommand_wrongDate_dukeExceptionThrown() {
        String s = "OOPS!!! Time of deadline is missing. Please indicate a stipulated time.";
        try {
            Parser.isCommand("deadline cook /by", Command.DEADLINE);
        } catch (DukeException e) {
            assertEquals(s, e.getMessage());
        }
    }

}
