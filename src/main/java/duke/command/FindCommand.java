package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * An instance of FindCommand.
 */
public class FindCommand extends Command {

    private final String findString;

    /**
     * Finds all the tasks that contains the keyword that was provided.
     *
     * @param findString the keyword that the user wants to find
     */
    public FindCommand(String findString) {
        this.findString = findString;
    }

    /**
     * Execute.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to show whether the keyword has been found or if the list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        } else {
            List<Task> filteredTask = tasks.getTasks().stream()
                    .filter(task -> task.getTaskName().contains(findString))
                    .collect(Collectors.toList());
            return ui.showFind(findString, filteredTask);
        }
    }
}
