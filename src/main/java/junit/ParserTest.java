package junit;
import duke.commands.*;
import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;
import duke.main.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest  {
    private final String tabbedLine = "\t----------------------------------------------";
    TaskList toDoList = new TaskList();
    @Test
    void parserTest() throws DukeException {
        assertTrue(Parser.parseCommands(Ui.Reply.LIST, toDoList, "list") instanceof ListCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.TODO, toDoList, "todo smth") instanceof AddToDoCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DEADLINE, toDoList, "deadline smth /by smth") instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.EVENT, toDoList, "event smth /at somewhere") instanceof AddEventCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.MARK, toDoList, "mark 1") instanceof MarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.UNMARK, toDoList, "unmark 1") instanceof UnmarkCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DELETE, toDoList, "delete 1") instanceof DeleteCommand);
        assertTrue(Parser.parseCommands(Ui.Reply.DEFAULT, toDoList, "dsadadsa") instanceof WrongCommand);
    }
}
