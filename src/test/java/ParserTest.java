import dukeclasses.DukeException;
import dukeclasses.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parse_blankInput_DukeExceptionThrown() {
        try {
            assertEquals(" ",  Parser.parse(" ").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }

    @Test
    public void parse_InvalidInput_DukeExceptionThrown() {
        try {
            assertEquals(" ",  Parser.parse(" ").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deadline",  Parser.parse("deadline tmr ").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("delete",  Parser.parse("delete t").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deleted",  Parser.parse("deleted 1").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("listed",  Parser.parse("listed").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event homework /by Tmr",  Parser.parse("deleted 1").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event homework /by 202-11-10",  Parser.parse("deleted 1").getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }
}
