package junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.DeleteCommand;
import duke.commands.DuplicateCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ParserTest {
    private TaskList toDoList = new TaskList();
    private Storage storage = new Storage("./tasklist.txt");

    @Test
    void parserTest() throws DukeException {
        assertTrue(Parser.parseCommands(Ui.Reply.LIST, toDoList, "list", storage) instanceof ListCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.TODO, toDoList, "todo smth", storage) instanceof AddToDoCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DEADLINE, toDoList,
                "deadline smthElse /by smth", storage) instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.EVENT, toDoList,
                "event smthElse2 /at somewhere", storage) instanceof AddEventCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.EVENT, toDoList,
                "event smthElse2 /at somewhere", storage) instanceof DuplicateCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.MARK, toDoList, "mark 1", storage) instanceof MarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.UNMARK, toDoList, "unmark 1", storage) instanceof UnmarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DELETE, toDoList, "delete 1", storage) instanceof DeleteCommand);
    }
}
