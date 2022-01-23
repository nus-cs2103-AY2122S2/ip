package commands;

import org.junit.jupiter.api.Test;
import task.ListTask;
import task.ToDo;

import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest {

    @Test
    public void exitCommandExecute_normalInput_success() {
        ExitCommand c1=new ExitCommand();
        ListTask tasks=new ListTask();
        assertEquals(c1.execute(tasks),ExitCommand.MESSAGE_EXIT);
    }

    @Test
    public void exitCommand_isExit(){
        ExitCommand c1=new ExitCommand();
        ToDo todo = new ToDo("report3");
        AddCommand c2=new AddCommand(todo);
        DeleteCommand c3=new DeleteCommand(1);
        ListCommand c4=new ListCommand();
        MarkCommand c5=new MarkCommand(1);
        UnMarkCommand c6=new UnMarkCommand(1);

        assertTrue(ExitCommand.isExit(c1));
        assertFalse(ExitCommand.isExit(c2));
        assertFalse(ExitCommand.isExit(c3));
        assertFalse(ExitCommand.isExit(c4));
        assertFalse(ExitCommand.isExit(c5));
        assertFalse(ExitCommand.isExit(c6));
    }
}
