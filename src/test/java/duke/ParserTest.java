package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

public class ParserTest {
    private String byeInput;
    private String listInput;
    private String deleteInput;
    private String markInput;
    private String unmarkInput;
    private String todoInput;
    private String deadlineInput;
    private String eventInput;
    private String findInput;
    private String invalidInput;

    public ParserTest() {
        this.byeInput = "bye";
        this.listInput = "list";
        this.deleteInput = "delete 1";
        this.markInput = "mark 1";
        this.unmarkInput = "unmark 1";
        this.todoInput = "todo go for a run";
        this.deadlineInput = "deadline finish assignment /by 2022-01-06";
        this.eventInput = "event group meeting /at 6 Feb 2022 3pm";
        this.findInput = "find run";
        this.invalidInput = "do something";
    }

    @Test
    void parseUserCommand_bye_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(byeInput);
        assertTrue(parserReturnCommand instanceof ByeCommand);
    }

    @Test
    void parseUserCommand_list_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(listInput);
        assertTrue(parserReturnCommand instanceof ListCommand);
    }

    @Test
    void parseUserCommand_delete_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(deleteInput);
        assertTrue(parserReturnCommand instanceof DeleteCommand);
    }

    @Test
    void parseUserCommand_mark_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(markInput);
        assertTrue(parserReturnCommand instanceof MarkCommand);
    }

    @Test
    void parseUserCommand_unmark_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(unmarkInput);
        assertTrue(parserReturnCommand instanceof UnmarkCommand);
    }

    @Test
    void parseUserCommand_todo_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(todoInput);
        assertTrue(parserReturnCommand instanceof AddCommand);
    }

    @Test
    void parseUserCommand_deadline_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(deadlineInput);
        assertTrue(parserReturnCommand instanceof AddCommand);
    }

    @Test
    void parseUserCommand_event_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(eventInput);
        assertTrue(parserReturnCommand instanceof AddCommand);
    }

    @Test
    void parseUserCommand_find_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(findInput);
        assertTrue(parserReturnCommand instanceof FindCommand);
    }

    @Test
    void parseUserCommand_invalid_success() throws DukeException {
        Parser parser = new Parser();
        Command parserReturnCommand = parser.parseUserCommand(invalidInput);
        assertTrue(parserReturnCommand instanceof InvalidCommand);
    }


}
