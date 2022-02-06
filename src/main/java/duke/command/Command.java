package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import duke.main.Ui;
import duke.exception.DukeException;

/**
 * Represents a command
 */
public abstract class Command {

    /**
     * Validates whether an exit command is made
     *
     * @return true if exit command is made, otherwise false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the specified command
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }
}
