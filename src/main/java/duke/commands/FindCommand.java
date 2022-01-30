package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds matching tasks according to a given keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new find command.
     *
     * @param keyword the keyword to be used
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the find command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredList = tasks.filter(keyword);

        if (filteredList.isEmpty()) {
            return "There are no matching tasks in your list!";
        } else {
            return "Here are the matching tasks I've found in your list:\n" + filteredList;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
