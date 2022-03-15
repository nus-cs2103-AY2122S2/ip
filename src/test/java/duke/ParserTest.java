package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;
import duke.commands.parser.Parser;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void todoParseTest() throws DukeException {
        Parser parser = new Parser();
        Command command = parser.parse("todo borrow book");
        assertTrue(command instanceof TodoCommand);
        TodoCommand todo = (TodoCommand) command;
        assertEquals(todo.getName(), "borrow book");
    }

    @Test
    public void deadlineParseTest() throws DukeException {
        Parser parser = new Parser();
        Command command = parser.parse("deadline project report /by 2012-04-03 0555");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void eventParseTest() throws DukeException {
        Parser parser = new Parser();
        Command command = parser.parse("event carnival /at 2012-04-03 0555 2012-05-03 0600");
        assertTrue(command instanceof EventCommand);
    }


}
