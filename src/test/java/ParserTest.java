import dukeclasses.DukeException;
import dukeclasses.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parse_blankInput_DukeExceptionThrown() {
        try {
            assertEquals(" ",  Parser.parse(" ", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }

    @Test
    public void parse_InvalidInput_DukeExceptionThrown() {
        try {
            assertEquals(" ",  Parser.parse(" ", -1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deadline",  Parser.parse("deadline tmr ", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("delete",  Parser.parse("delete t", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deleted",  Parser.parse("deleted 1", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("listed",  Parser.parse("listed", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event",  Parser.parse("event homework /by Tmr", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event",  Parser.parse("event homework /by 202-11-10", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }

}
