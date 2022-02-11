import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_incorrectInput_exceptionThrown() throws DukeException {
        try {
            assertEquals(0, Parser.parse("nonsense command", "random stuff"));
            fail();
        } catch (DukeException de) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", de.getMessage());
        }
    }

    @Test
    public void parse_incorrectDate_exceptionThrow() throws DukeException {
        try {
            assertEquals(0, Parser.parse("deadline", "cs2013 homework /by 2022/20/01"));
            fail();
        } catch (DukeException de) {
            assertEquals("Please use /at and type the date with the format yyyy-mm-dd after it", de.getMessage());
        }
    }

}
