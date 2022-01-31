package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deals with handling command that is unknown.
 */
public class NotKnownCommand extends Command {

    /**
     * Prints out the error message.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return show error message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("OOPS!!! I'm sorry, but I don't know what that means :<");
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
