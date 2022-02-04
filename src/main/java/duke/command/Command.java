package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command is the action that needs to be taken to carry out the user's request.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Command constructor
     *
     * @throws DukeException as the user might leave out crtiical information or input wrong commands.
     */
    public Command() throws DukeException {
    }

    /**
     * Kickstarts the actions to execute in order to carry out the command.
     *
     * @param tasks   holds all the tasks that the user has recorded down.
     * @param ui      used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     * @throws DukeException
     */
    public abstract String execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * checks whether the the command was an exit command.
     * @return boolean flag
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Flips the exit status to true.
     */
    protected void startExit() {
        this.isExit = true;
    }

}
