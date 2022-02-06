package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An instance of ListCommand.
 */
public class ListCommand extends Command {
    /**
     * Prints out the content of `TaskList` with its respective entry numbers.
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return the entries of the list in string format
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        } else {
            return ui.showList(tasks);
        }
    }
}
