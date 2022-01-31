package bob;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.ToDoCommand;
import bob.exception.DeadlineException;
import bob.exception.EventException;
import bob.exception.InvalidCommandException;
import bob.exception.ToDoException;




class ParserTest {
    @Test
    public void parse() {
        String toDoInput = "todo feed the dogs";
        assertTrue(Parser.parse(toDoInput) instanceof ToDoCommand);
        String deadlineInput = "deadline homework /by 2022-01-31T23:59";
        assertTrue(Parser.parse(deadlineInput) instanceof DeadlineCommand);
        String eventInput = "Event seminar /at 2022-01-31T22:59-23:59";
        assertTrue(Parser.parse(eventInput) instanceof EventCommand);
        String markInput = "mark 1";
        assertTrue(Parser.parse(markInput) instanceof MarkCommand);
        String deleteInput = "delete 1";
        assertTrue(Parser.parse(deleteInput) instanceof DeleteCommand);
        String listInput = "list";
        assertTrue(Parser.parse(listInput) instanceof ListCommand);
    }

    @Test
    public void parse_exceptionThrown() {
        String toDoInput = "todo";
        assertThrows(ToDoException.class, () -> Parser.parse(toDoInput));
        String deadlineInput = "deadline homework /by invalid date";
        assertThrows(DeadlineException.class, () -> Parser.parse(deadlineInput));
        String eventInput = "Event seminar /at invalid date";
        assertThrows(EventException.class, () -> Parser.parse(eventInput));
        String markInput = "mark one";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(markInput));
        String deleteInput = "delete two";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(deleteInput));
        String emptyInput = "invalid command";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(emptyInput));
    }
}
