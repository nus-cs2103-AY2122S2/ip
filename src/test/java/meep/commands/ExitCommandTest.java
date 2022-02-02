package meep.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import meep.task.ListTask;
import meep.task.ToDo;


public class ExitCommandTest {

    @Test
    public void exitCommandExecute_normalInput_success() {
        ExitCommand c1 = new ExitCommand();
        ListTask tasks = new ListTask();
        assertEquals(c1.execute(tasks), ExitCommand.MESSAGE_EXIT);
    }

    @Test
    public void exitCommand_isExit() {
        ExitCommand c1 = new ExitCommand();
        ToDo todo = new ToDo("report3");
        AddCommand c2 = new AddCommand(todo);
        DeleteCommand c3 = new DeleteCommand(1);
        ListCommand c4 = new ListCommand();
        MarkCommand c5 = new MarkCommand(1);
        UnmarkCommand c6 = new UnmarkCommand(1);

        assertTrue(ExitCommand.isExit(c1));
        assertFalse(ExitCommand.isExit(c2));
        assertFalse(ExitCommand.isExit(c3));
        assertFalse(ExitCommand.isExit(c4));
        assertFalse(ExitCommand.isExit(c5));
        assertFalse(ExitCommand.isExit(c6));
    }
}
