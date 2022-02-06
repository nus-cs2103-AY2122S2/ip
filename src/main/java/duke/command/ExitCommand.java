package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Ends the program.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //includes closing scanner
        ui.farewell();
    }

    /**
     * Returns true so that the Duke exits.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
