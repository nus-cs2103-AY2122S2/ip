package sparrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.Command;
import sparrow.logic.parser.CommandParser;

public class CommandParserTest {
    @Test
    public void parse_validByeCommand_success() {
        try {
            Command c = CommandParser.parse("bye");
            assertTrue(c.isExit());
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidToDoCommand_exceptionThrown() {
        try {
            assertEquals("foo", CommandParser.parse("todo"));
            fail();
        } catch (ParseException e) {
            assertEquals("The description of a to-do cannot be empty.", e.getMessage());
        }
    }
}
