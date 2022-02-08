package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Searches for tasks that match the keywords.
 */
public class FindCommand extends Command{
    protected String search;
    private static final String MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Constructs a find command.
     *
     * @param search The search string for matching.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = new TaskList(tasks.findTasks(search));
        return ui.showMessage(result.taskListToString());
    }
}
