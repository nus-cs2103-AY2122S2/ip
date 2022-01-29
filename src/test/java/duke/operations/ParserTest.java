package duke.operations;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    public void deadLineIsExitIsFalse_addCommand_success() throws DukeException {
        try {
            assertEquals("false", String.valueOf(Parser.parse("do project /by 23-12-2022 1900").isExit()));
        } catch (DukeException ignored) {
            System.out.println("nothing");
        }
    }

    @Test
    public void byeIsExitIsTrue_exitCommand_success() throws DukeException {
        try {
            assertEquals("true", String.valueOf(Parser.parse("bye").isExit()));
        } catch (DukeException ignored) {
            System.out.println("nothing");
        }
    }

    @Test
    public void deadLineOutput_stringToDate_exceptionThrown() throws DukeException {
        try {
            assertEquals(0,
                    Parser.parse("deadline im dumb /by 11231123"));
        } catch (DukeException e) {
            assertEquals("you sussy baka, that's the wrong date format! Enter dd-MM-yyyy HHmm", e.getMessage());
        }
    }

    @Test
    public void eventOutput_stringToDate_exceptionThrown() throws DukeException {
        try {
            assertEquals(0,
                    Parser.parse("event im dumb /at 11231123"));
        } catch (DukeException e) {
            assertEquals("you sussy baka, that's the wrong date format! Enter dd-MM-yyyy HHmm-HHmm", e.getMessage());
        }
    }
}
