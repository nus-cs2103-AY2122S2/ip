package bob;

import bob.command.*;
import bob.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {
    @Test
    public void parseTest() {
        String ToDoInput = "todo feed the dogs";
        assertTrue(Parser.parse(ToDoInput) instanceof ToDoCommand);
        String DeadlineInput = "deadline homework /by 2022-01-31T23:59";
        assertTrue(Parser.parse(DeadlineInput) instanceof DeadlineCommand);
        String EventInput = "Event seminar /at 2022-01-31T22:59-23:59";
        assertTrue(Parser.parse(EventInput) instanceof EventCommand);
        String MarkInput = "mark 1";
        assertTrue(Parser.parse(MarkInput) instanceof MarkCommand);
        String DeleteInput = "delete 1";
        assertTrue(Parser.parse(DeleteInput) instanceof DeleteCommand);
        String ListInput = "list";
        assertTrue(Parser.parse(ListInput) instanceof ListCommand);
    }

    @Test
    public void parseExceptionTest() {
        String ToDoInput = "todo";
        assertThrows(ToDoException.class,
                () -> Parser.parse(ToDoInput));
        String DeadlineInput = "deadline homework /by invalid date";
        assertThrows(DeadlineException.class,
                () -> Parser.parse(DeadlineInput));
        String EventInput = "Event seminar /at invalid date";
        assertThrows(EventException.class,
                () -> Parser.parse(EventInput));
        String MarkInput = "mark one";
        assertThrows(InvalidCommandException.class,
                () -> Parser.parse(MarkInput));
        String DeleteInput = "delete two";
        assertThrows(InvalidCommandException.class,
                () -> Parser.parse(DeleteInput));
        String emptyInput = "invalid command";
        assertThrows(InvalidCommandException.class,
                () -> Parser.parse(emptyInput));
    }
}