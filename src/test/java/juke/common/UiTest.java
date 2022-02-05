package juke.common;

import juke.command.AddCommand;
import juke.command.DeleteCommand;
import juke.command.EchoCommand;
import juke.command.ExitCommand;
import juke.command.FindCommand;
import juke.command.ListCommand;
import juke.command.MarkCommand;
import juke.exception.JukeInvalidCommandException;
import juke.task.TaskStatus;
import juke.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {
    @Test
    public void getCommand_invalidCommand_exceptionThrown() {
        try {
            assertEquals(0, new Ui().getCommand("aaa"));
            fail();
        } catch (JukeInvalidCommandException e) {
            assertEquals("[ERROR] Invalid command: aaa.", e.getMessage());
        }
    }
    
    @Test
    public void getCommand_blankCommand_nullReturned() {
        try {
            assertEquals(null, new Ui().getCommand(""));
        } catch (JukeInvalidCommandException e) {
           fail();
        }
    }
    
    @Test
    public void getCommand_validCommand_commandReturned() {
        try {
            assertEquals(new EchoCommand().getClass(),
                    new Ui().getCommand("echo").getClass());
            assertEquals(new ExitCommand().getClass(),
                    new Ui().getCommand("bye").getClass());
            assertEquals(new ListCommand().getClass(),
                    new Ui().getCommand("list").getClass());
            assertEquals(new AddCommand(TaskType.TODO).getClass(),
                    new Ui().getCommand("todo").getClass());
            assertEquals(TaskType.TODO,
                    ((AddCommand) new Ui().getCommand("todo")).getType());
            assertEquals(new AddCommand(TaskType.EVENT).getClass(),
                    new Ui().getCommand("event").getClass());
            assertEquals(TaskType.EVENT,
                    ((AddCommand) new Ui().getCommand("event")).getType());
            assertEquals(new AddCommand(TaskType.DEADLINE).getClass(),
                    new Ui().getCommand("deadline").getClass());
            assertEquals(TaskType.DEADLINE,
                    ((AddCommand) new Ui().getCommand("deadline")).getType());
            assertEquals(new MarkCommand(TaskStatus.DONE).getClass(),
                    new Ui().getCommand("mark").getClass());
            assertEquals(TaskStatus.DONE,
                    ((MarkCommand) new Ui().getCommand("mark")).getStatus());
            assertEquals(new MarkCommand(TaskStatus.NOT_DONE).getClass(),
                    new Ui().getCommand("unmark").getClass());
            assertEquals(TaskStatus.NOT_DONE,
                    ((MarkCommand) new Ui().getCommand("unmark")).getStatus());
            assertEquals(new DeleteCommand().getClass(),
                    new Ui().getCommand("delete").getClass());
            assertEquals(new FindCommand().getClass(),
                new Ui().getCommand("find").getClass());
        } catch (JukeInvalidCommandException e) {
            fail();
        }
    }
}
