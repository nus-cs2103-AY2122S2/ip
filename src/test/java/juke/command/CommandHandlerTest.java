package juke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import juke.exception.JukeInvalidCommandException;
import juke.task.TaskStatus;
import juke.task.TaskType;

public class CommandHandlerTest {
    @Test
    public void getCommand_invalidCommand_exceptionThrown() {
        try {
            assertEquals(0, CommandHandler.fetchCommand("aaa"));
            fail();
        } catch (JukeInvalidCommandException e) {
            assertEquals("[ERROR] Invalid command: aaa.", e.getMessage());
        }
    }

    @Test
    public void getCommand_blankCommand_nullReturned() {
        try {
            assertEquals(null, CommandHandler.fetchCommand(""));
        } catch (JukeInvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validCommand_commandReturned() {
        try {
            assertEquals(new EchoCommand().getClass(),
                    CommandHandler.fetchCommand("echo").getClass());
            assertEquals(new ExitCommand().getClass(),
                    CommandHandler.fetchCommand("bye").getClass());
            assertEquals(new ListCommand().getClass(),
                    CommandHandler.fetchCommand("list").getClass());
            assertEquals(new AddCommand(TaskType.TODO).getClass(),
                    CommandHandler.fetchCommand("todo").getClass());
            assertEquals(TaskType.TODO, (
                    (AddCommand) CommandHandler.fetchCommand("todo")).getType());
            assertEquals(new AddCommand(TaskType.EVENT).getClass(),
                    CommandHandler.fetchCommand("event").getClass());
            assertEquals(TaskType.EVENT, (
                    (AddCommand) CommandHandler.fetchCommand("event")).getType());
            assertEquals(new AddCommand(TaskType.DEADLINE).getClass(),
                    CommandHandler.fetchCommand("deadline").getClass());
            assertEquals(TaskType.DEADLINE, (
                    (AddCommand) CommandHandler.fetchCommand("deadline")).getType());
            assertEquals(new MarkCommand(TaskStatus.DONE).getClass(),
                    CommandHandler.fetchCommand("mark").getClass());
            assertEquals(TaskStatus.DONE, (
                    (MarkCommand) CommandHandler.fetchCommand("mark")).getStatus());
            assertEquals(new MarkCommand(TaskStatus.NOT_DONE).getClass(),
                    CommandHandler.fetchCommand("unmark").getClass());
            assertEquals(TaskStatus.NOT_DONE, (
                    (MarkCommand) CommandHandler.fetchCommand("unmark")).getStatus());
            assertEquals(new DeleteCommand().getClass(),
                    CommandHandler.fetchCommand("delete").getClass());
            assertEquals(new FindCommand().getClass(),
                    CommandHandler.fetchCommand("find").getClass());
        } catch (JukeInvalidCommandException e) {
            fail();
        }
    }
}
