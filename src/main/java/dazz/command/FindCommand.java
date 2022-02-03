package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

/**
 * Represents the logic of finding <code>Task</code> in <code>TaskList</code>.
 */
public class FindCommand extends Command {
    private final String search;

    /**
     * Constructs a <code>FindCommand</code> based on the search criteria.
     * @param description The description of the search criteria.
     */
    public FindCommand(String description) {
        this.search = description;
    }

    /**
     * Executes the finding of a <code>Task</code> and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to find.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //ui.showSearches(taskList, search);
        return ui.messageForSearches(taskList, search);
    }
}
