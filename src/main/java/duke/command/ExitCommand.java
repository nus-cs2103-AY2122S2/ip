package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents exit command
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays exit message
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        System.exit(0);
    }
}
