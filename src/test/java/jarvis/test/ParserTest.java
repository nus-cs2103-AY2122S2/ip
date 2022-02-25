package jarvis.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.commands.AddCommand;
import jarvis.commands.Command;
import jarvis.commands.DeleteCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.QuitCommand;
import jarvis.commands.UnmarkCommand;
import jarvis.exceptions.InvalidCommandException;
import jarvis.utils.Parser;

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
