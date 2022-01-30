package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that displays a list of tasks.
 *
 * @author Peter
 */
public class ListCommand extends Command {
    /**
     * Displays a given list of tasks.
     *
     * @param taskList List of tasks that is to be displayed.
     * @param ui       UI responsible for displaying list of tasks.
     * @param storage  Storage ignored.
     * @return <code>true</code> upon successful execution.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("YOUR TASKS:" + taskList);
        return true;
    }
}
