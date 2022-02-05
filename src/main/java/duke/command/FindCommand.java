package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to find tasks according to a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String k) {
        keyword = k;
    }

    public boolean isExit() {
        return false;
    }
    /**
     * Executes the command.
     *
     * @param storage  The storage.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @throws DukeException if there is a problem updating the storage or user interface.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.filter(keyword);
        return ui.filterTasks(filteredTasks);
    }
}
