package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a type of Command - Exit.
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the program by changing the state of isExit.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTerminatingMessage();
        setIsExit(true);
    }

}
