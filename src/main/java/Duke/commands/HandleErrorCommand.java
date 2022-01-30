package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

/**
 * Deals with handling command for errors.
 */
public class HandleErrorCommand extends Command {
    private String errorMsg;

    /**
     * Constructor for HandleErrorCommand.
     *
     * @param errorMsg error message to be display.
     */
    public HandleErrorCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Displays error message to user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showError(errorMsg);
    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return false that user not exiting.
     */
    public boolean isExit() {
        return false;
    }
}
