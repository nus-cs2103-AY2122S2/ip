package taskie.command;

import taskie.storage.Storage;
import taskie.task.Task;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that specifies the behavior of a command that finds tasks from a given task list.
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        super("find");
        this.input = input;
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     * @param response StringBuilder object to append results to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        List<Task> result = getSearchResultList(tasks);

        if (result.size() != 0) {
            response.append(ui.listFindResults(result));
        } else {
            response.append(ui.taskNotFound());
        }

        assert response.length() > 0; // response should not be empty
    }

    /**
     * Filters a given TaskList for tasks that contains a certain string.
     *
     * @param tasks The TaskList to filter.
     * @return A new List of Tasks that contains tasks that contains the desired string.
     */
    private List<Task> getSearchResultList(TaskList tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.list()) {
            if (task.toString().contains(input)) {
                result.add(task);
            }
        }
        return result;
    }
}
