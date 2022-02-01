package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a general command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command by utilising the parameters as stated in the overriden method.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     *
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;


    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    public abstract boolean isExit();
}
