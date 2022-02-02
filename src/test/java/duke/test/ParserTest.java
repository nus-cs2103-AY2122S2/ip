package duke.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.QuitCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.InvalidCommandException;

public class ParserTest {
    @Test
    public void parseCmd_validCmd() throws InvalidCommandException {
        String cmdString = "bye";
        Command cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof QuitCommand);

        cmdString = "list";
        cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof ListCommand);

        cmdString = "mark 1";
        cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof MarkCommand);

        cmdString = "unmark 1";
        cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof UnmarkCommand);

        cmdString = "delete 1";
        cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof DeleteCommand);

        cmdString = "todo borrow book";
        cmd = Parser.parse(cmdString);
        assertTrue(cmd instanceof AddCommand);
    }

    @Test void parseCmd_invalidCmd_exceptionThrown() {
        String cmdString = "";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(cmdString));
    }

    @Test
    public void parseCmd_invalidIdx_exceptionThrown() {
        String cmdString = "delete hello";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(cmdString));
    }
}
