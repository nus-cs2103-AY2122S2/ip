package task;

import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import data.DukeException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void Parser_Command_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }
}
