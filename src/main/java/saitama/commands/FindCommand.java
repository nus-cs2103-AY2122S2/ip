package saitama.commands;

import java.util.List;

import saitama.storage.Storage;
import saitama.tasks.Task;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object that searches the task list for tasks that matches the given query.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Initialises the FindCommand.
     *
     * @param query The String to search in the TaskList
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.search(query);
        return ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
