package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Searches for tasks that match the keywords.
 */
public class FindCommand extends Command {
    private static final String MESSAGE = "Here are the matching tasks in your list:";

    protected String search;
    /**
     * Constructs a find command.
     *
     * @param search The search string for matching.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList result = new TaskList(tasks.findTasks(search));
        return MESSAGE + "\n" + result.taskListToString();
    }
}
