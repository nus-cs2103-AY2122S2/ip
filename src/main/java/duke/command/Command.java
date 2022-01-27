package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;

/**
 * Represents a general command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command by utilising the parameters in the stated in the overriden method.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     * @throws DukeException If there is an issue saving the tasks.
     */
    public abstract void execute(TaskList taskList,Ui ui, Storage storage) throws DukeException;


    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    public abstract boolean isExit();
}
