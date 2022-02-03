package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a type of Command - Incorrect.
 * Handles unrecognized commands.
 */
public class IncorrectCommand extends Command {
    protected String errorMessage = "";

    /**
     * Default class constructor.
     */
    public IncorrectCommand() {

    }

    /**
     * Class constructor.
     * Assigns custom error message.
     *
     * @param errorMessage Custom error messages
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Handles incorrect commands.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException If error is thrown, forward the error message
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.errorMessage.equals("")) {
            throw new DukeException(ui.showIncorrectMessage());
        } else {
            throw new DukeException(this.errorMessage);
        }
    }

}
