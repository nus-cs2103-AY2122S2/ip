package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        return ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
