package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An instance of ClearCommand.
 */
public class ClearCommand extends Command {

    /**
     * Clears the `TaskList` that is in memory.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to inform the user that the list has been cleared
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO: warning
        tasks.getTasks().clear();
        return "Your task list has been cleared.";
    }
}
