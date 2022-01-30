package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that clears a list of tasks.
 *
 * @author Peter
 */
public class ClearCommand extends Command {
    /**
     * Clears a given list of tasks, displays a response message, and truncates a local file
     * associated with the list of tasks.
     *
     * @param taskList List of tasks that is to be cleared.
     * @param ui       UI responsible for displaying the response message.
     * @param storage  Storage responsible for truncating local file.
     * @return <code>true</code> upon successful execution.
     * @throws DukeException If file truncation is unsuccessful.
     */
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.clearFile();
        taskList.clear();
        ui.showMessage("ALL TASKS CLEARED");
        return true;
    }
}
