package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An instance of ExitCommand.
 */
public class ExitCommand extends Command {

    /**
     * Writes this `TaskList` into the local file and closes the `Ui`.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to inform the user that the app is going to close.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.close();
    }

    /**
     * Returns `true` so that the program can proceed to gracefully close.
     * @return true to signal the application to close.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
