package juke.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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
import juke.ui.TextUi;

public class TextUiTest {
    @Test
    public void getCommand_invalidCommand_exceptionThrown() {
        try {
            assertEquals(0, new TextUi().getCommand("aaa"));
            fail();
        } catch (JukeInvalidCommandException e) {
            assertEquals("[ERROR] Invalid command: aaa.", e.getMessage());
        }
    }

    @Test
    public void getCommand_blankCommand_nullReturned() {
        try {
            assertEquals(null, new TextUi().getCommand(""));
        } catch (JukeInvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validCommand_commandReturned() {
        try {
            assertEquals(new EchoCommand().getClass(),
                    new TextUi().getCommand("echo").getClass());
            assertEquals(new ExitCommand().getClass(),
                    new TextUi().getCommand("bye").getClass());
            assertEquals(new ListCommand().getClass(),
                    new TextUi().getCommand("list").getClass());
            assertEquals(new AddCommand(TaskType.TODO).getClass(),
                    new TextUi().getCommand("todo").getClass());
            assertEquals(TaskType.TODO, (
                    (AddCommand) new TextUi().getCommand("todo")).getType());
            assertEquals(new AddCommand(TaskType.EVENT).getClass(),
                    new TextUi().getCommand("event").getClass());
            assertEquals(TaskType.EVENT, (
                    (AddCommand) new TextUi().getCommand("event")).getType());
            assertEquals(new AddCommand(TaskType.DEADLINE).getClass(),
                    new TextUi().getCommand("deadline").getClass());
            assertEquals(TaskType.DEADLINE, (
                    (AddCommand) new TextUi().getCommand("deadline")).getType());
            assertEquals(new MarkCommand(TaskStatus.DONE).getClass(),
                    new TextUi().getCommand("mark").getClass());
            assertEquals(TaskStatus.DONE, ((MarkCommand) new TextUi().getCommand("mark")).getStatus());
            assertEquals(new MarkCommand(TaskStatus.NOT_DONE).getClass(),
                    new TextUi().getCommand("unmark").getClass());
            assertEquals(TaskStatus.NOT_DONE, (
                    (MarkCommand) new TextUi().getCommand("unmark")).getStatus());
            assertEquals(new DeleteCommand().getClass(),
                    new TextUi().getCommand("delete").getClass());
            assertEquals(new FindCommand().getClass(),
                    new TextUi().getCommand("find").getClass());
        } catch (JukeInvalidCommandException e) {
            fail();
        }
    }
}
