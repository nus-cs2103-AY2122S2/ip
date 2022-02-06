package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ModifyCommand;
import duke.exception.DukeException;
import duke.task.TaskList;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParser() throws DukeException {
        TaskList taskList = new TaskList();

        taskList.addTask(new Todo("testing"));

        assertTrue(Parser.parse("list", taskList) instanceof ListCommand);

        assertTrue(Parser.parse("bye", taskList) instanceof ByeCommand);

        assertTrue(Parser.parse("todo drink water", taskList) instanceof AddCommand);

        assertTrue(Parser.parse("mark 1", taskList) instanceof ModifyCommand);

        assertTrue(Parser.parse("invalid command", taskList) instanceof InvalidCommand);
    }
}
