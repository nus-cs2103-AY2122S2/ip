package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;
import duke.main.DukeException;
import duke.main.Storage;

import duke.commands.AddEventCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeleteCommand;
import duke.commands.WrongCommand;


public class ParserTest {
    TaskList toDoList = new TaskList();
    Storage storage = new Storage("./tasklist.txt");
    @Test
    void parserTest() throws DukeException {
        assertTrue(Parser.parseCommands(Ui.Reply.LIST, toDoList, "list", storage) instanceof ListCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.TODO, toDoList, "todo smth", storage) instanceof AddToDoCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DEADLINE, toDoList, "deadline smth /by smth", storage) instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.EVENT, toDoList, "event smth /at somewhere", storage) instanceof AddEventCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.MARK, toDoList, "mark 1", storage) instanceof MarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.UNMARK, toDoList, "unmark 1", storage) instanceof UnmarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DELETE, toDoList, "delete 1", storage) instanceof DeleteCommand);
    }
}
