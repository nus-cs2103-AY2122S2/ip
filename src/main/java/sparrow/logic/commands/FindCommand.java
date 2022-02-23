package sparrow.logic.commands;

import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

/**
 * Represents the command to find tasks according to a keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

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
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.filter(keyword);
        return ui.filterTasks(filteredTasks);
    }
}
