package duke.commands;

import duke.Message;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

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
        ArrayList<Task> result = tasks.findTasks(search);
        StringBuilder find = new StringBuilder();
        find.append(MESSAGE);
        for (Task task : result) {
            find.append("\n  " + task.toString());
        }
        return find.toString();
    }
}
