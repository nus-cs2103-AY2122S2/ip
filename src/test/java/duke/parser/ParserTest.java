package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.*;
import duke.exceptions.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void parseExitCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("bye") instanceof ExitCommand);
    }

    @Test
    public void parseListCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("list") instanceof ListCommand);
    }

    @Test
    public void parseMarkCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parseUnmarkCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parseDeleteCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parseTodoCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("TODO return book") instanceof AddCommand);
    }

    @Test
    public void parseDeadlineCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("DeADLINE return book /by 01/01/2000 10:10") instanceof AddCommand);
    }

    @Test
    public void parseEventCommandsTest() throws DukeInvalidArgumentException{
        assertTrue(parser.parseCommands("EvEnt return book /at 01/01/2000 10:10") instanceof AddCommand);
    }
}
