package pikabot.command;

import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.InvalidTaskCommandException;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends Command {

    private String[] invalidCommand;

    /**
     * Constructs an InvalidCommand.
     *
     * @param invalidCommand String array containing input string from user.
     */
    public InvalidCommand(String[] invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    /**
     * Throws an InvalidTaskCommandException when method is invoked.
     *
     * @throws InvalidTaskCommandException When method is invoked.
     */
    public static void invalidTask() throws InvalidTaskCommandException {
        throw new InvalidTaskCommandException();
    }

    /**
     * Executes InvalidCommand by printing out a InvalidTaskCommandException message.
     *
     * @param taskList TaskList containing current tasks.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            invalidTask();
        } catch (InvalidTaskCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
