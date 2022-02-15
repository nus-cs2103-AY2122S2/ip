package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a command that will exit the program upon execution.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by printing a farewell message.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Hope to see you again!";
    }

    /**
     * Returns True if it is an exit command and false otherwise.
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
