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
     * Displays a list of events that match the keyword given by the user.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return list of events that match the keyword given by the user, if any
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        } else {
            // already does "fuzzy" search
            List<Task> filteredTask = tasks.getTasks().stream()
                    .filter(task -> task.getTaskName().contains(findString))
                    .collect(Collectors.toList());
            return ui.showFind(findString, filteredTask);
        }
    }
}
