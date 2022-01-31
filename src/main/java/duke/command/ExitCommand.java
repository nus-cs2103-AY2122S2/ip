package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

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
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showExitMessage();
    }

    /**
     * Returns a boolean value that tells the programme to exit.
     *
     * @return Boolean value true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
