package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Abstract class that stores information about whether the command bye has been inputted, and executes chunks of code
 * on the users' input.
 */
public abstract class Command {

    private boolean isBye;

    /**
     * Constructor method for the Command class.
     */
    public Command() {
        isBye = false;
    }

    /**
     * Abstract method which is meant to execute a chunk of code based on the users' input.
     *
     * @param tasks TaskList that is maintained in Ducky.
     * @param ui Ui that is maintained in Ducky.
     * @param storage Storage that is maintained in Ducky.
     * @throws DukeException When the Index of the task is non-existent or there is no description of the task.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit(){
        return isBye;
    }

    public void setExit() {
        isBye = true;
    }

}
