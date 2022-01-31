package duke.parser;

import duke.DukeException;
import duke.commands.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_addCommand() {
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
        assertTrue(Parser.parse("deadline read book /by 2/2/2022 2200") instanceof AddCommand);
        assertTrue(Parser.parse("event read book /at 2/2/2022 2200") instanceof AddCommand);
    }

    @Test
    public void parse_deleteCommand() {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_findCommand() {
        assertTrue(Parser.parse("find book") instanceof FindCommand);
    }

    @Test
    public void parse_listCommand() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_exitCommand() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_markCommand() {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommand() {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parse_unknownCommand() {
        assertTrue(Parser.parse("hi") instanceof UnknownCommand);
    }

    @Test
    public void parse_noTodoDescription_exceptionThrown() {
        try {
            Parser.parse("todo      ");
            fail();
        } catch (DukeException e) {
            assertEquals("Missing details! Please use the format: todo <description>", e.getMessage());
        }
    }

    @Test
    public void parse_hasTodoDescription_success() {
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
        assertTrue(Parser.parse("todo homework") instanceof AddCommand);
        assertTrue(Parser.parse("todo errand") instanceof AddCommand);
    }
}
