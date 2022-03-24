package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to exit the Duke program.
 */
public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        storage.updateStorage(tasks);
        return ui.showFarewellMessage();
    }
}
