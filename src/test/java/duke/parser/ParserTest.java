package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_invalidMarkCommandFormat() {
        try {
            Parser.parse("mark ??");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The index of task to mark is not a valid integer");
        }
    }

    @Test
    public void parse_incompleteMarkCommand() {
        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Seems like the command is incomplete");
        }
    }

    @Test
    public void parse_invalidDeadlineCommandFormat() {
        try {
            Parser.parse("deadline iP /by 2022-99-01");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The date provided is invalid(or in wrong format)");
        }
    }

    @Test
    public void parse_invalidCommand() {
        try {
            Parser.parse("bruh");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "I'm sorry, but I don't know what that means");
        }
    }
}
