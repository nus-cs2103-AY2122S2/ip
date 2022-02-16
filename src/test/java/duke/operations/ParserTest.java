package duke.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;


class ParserTest {

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
