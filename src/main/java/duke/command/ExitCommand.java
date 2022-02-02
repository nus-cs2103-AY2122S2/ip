package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which tells the main programme to exit. An ExitCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class ExitCommand implements Command {

    /**
     * Output an exit message to the user.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     */

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) {
        return ui.showExitMessage();
    }
}
