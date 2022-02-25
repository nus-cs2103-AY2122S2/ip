package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to display list of all tasks
 */
public class ListCommand extends Command {
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
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.showMessage("You have 0 tasks so far!");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i=1; i<=tasks.size(); i++) {
                ui.showMessage((i) + ". " + tasks.get(i));
            }
        }
    }
}
