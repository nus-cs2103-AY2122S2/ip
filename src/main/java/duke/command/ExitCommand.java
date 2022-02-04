package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Deals with shutting down the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructor
     * @throws DukeException
     */
    public ExitCommand() throws DukeException {

    }

    /**
     * Exits the application and notifies the user.
     * @param tasks holds all the tasks that the user has recorded down.
     * @param ui used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     * @throws DukeException
     */
    @Override
    public String execute(TaskMaster tasks, Ui ui, Storage storage) {
        this.startExit();
        return ui.goodbye();
    }
}
