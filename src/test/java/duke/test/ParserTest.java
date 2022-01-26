package duke.test;

import static org.junit.jupiter.api.Assertions.*;

import duke.Parser;
import duke.commands.*;
import duke.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTest() throws InvalidCommandException {
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

    @Test void invalidActionTest() {
        String cmdString = "";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(cmdString));
    }

    @Test
    public void invalidIdxTest() {
        String cmdString = "delete hello";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(cmdString));
    }
}
