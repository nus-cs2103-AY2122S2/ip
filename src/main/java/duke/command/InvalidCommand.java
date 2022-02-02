package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {
    /**
     * Prints out that the command given is invalid.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command.");
    }
}
