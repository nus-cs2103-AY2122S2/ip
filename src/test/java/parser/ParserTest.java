package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.parser.Parser;

public class ParserTest {
    @Test
    public void wrongCommand() {
        try {
            Command c = Parser.parse("hello world");
            fail();
        } catch (Exception e) {
            assertEquals("I'm sorry, but I don't know what that means\n", e.getMessage());
        }
    }

    @Test
    public void noDescription() {
        try {
            Command c = Parser.parse("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a deadline cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void properDelete() {
        try {
            Command c = Parser.parse("delete 12 34 56");
            fail();
        } catch (Exception e) {
            assertEquals("Fill in proper integer for deletion.\n", e.getMessage());
        }
    }
}
