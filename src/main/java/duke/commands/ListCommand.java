package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to display list of all tasks
 */
public class ListCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        String output = "";

        if (tasks.size() <= 0) {
            output += "You have 0 tasks so far!";
        } else {
            output += "Here are the tasks in your list:\n";
            for (int i = 1; i <= tasks.size(); i++) {
                output += i + ". " + tasks.get(i) + "\n";
            }
        }

        return output;
    }
}
