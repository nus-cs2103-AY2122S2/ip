package duke.commands;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.bye();
    }

    /**
     * Notifies the main function to end the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
