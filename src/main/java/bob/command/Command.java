package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

/**
 * Represents the command that will be executed by the program.
 */
public abstract class Command {
    /**
     * Executes the command that is created based on the user's input.
     * @param tasks a List of tasks the user has
     * @param ui a class that deals with interactions with the user
     * @param store represents the file storage of the program
     * @throws BobException exceptions that tells Bob to let the user know what went wrong
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage store) throws BobException;
}
