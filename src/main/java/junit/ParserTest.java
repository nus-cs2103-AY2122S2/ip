package junit;

import echo.command.*;
import echo.main.EchoException;
import echo.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParse() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
            assertTrue(Parser.parse("todo borrow book") instanceof TodoCommand);
            assertTrue(Parser.parse("deadline sell book /by 2022-01-01 1800") instanceof DeadlineCommand);
            assertTrue(Parser.parse("event get rich /at 2022-04-20 0400") instanceof EventCommand);
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
            assertTrue(Parser.parse("hi") instanceof HelpCommand);
        } catch (EchoException e) {
            fail(); // should not reach here
        }
    }
}
