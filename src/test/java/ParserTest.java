import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dukeclasses.DukeException;
import dukeclasses.Parser;

public class ParserTest {

    @Test
    public void parse_blankInput_dukeExceptionThrown() {
        try {
            assertEquals(" ", Parser.parseUserInput(" ", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }

    @Test
    public void parse_invalidInput_dukeExceptionThrown() {
        try {
            assertEquals(" ", Parser.parseUserInput(" ", -1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deadline", Parser.parseUserInput("deadline tmr ", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("delete", Parser.parseUserInput("delete t", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("deleted", Parser.parseUserInput("deleted 1", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("listed", Parser.parseUserInput("listed", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event", Parser.parseUserInput("event homework /by Tmr", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }

        try {
            assertEquals("event", Parser.parseUserInput("event homework /by 202-11-10", 1).getCommand());
            fail();
        } catch (DukeException errorMessage) {
            assertEquals("    Invalid input detected. Please check your input", errorMessage.getMessage());
        }
    }

}
