package duke.task;

import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.data.DukeException;
import org.junit.jupiter.api.Test;
import duke.parser.Parser;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void Parser_Command_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }
}
