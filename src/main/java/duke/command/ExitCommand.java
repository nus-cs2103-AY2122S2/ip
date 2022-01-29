package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    /**
     * Writes this `TaskList` into the local file and closes the `Ui`.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.close();
    }

    /**
     * Returns `true` to terminate the program.
     *
     * @return `true` if the `Command` is `ExitCommand`
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
