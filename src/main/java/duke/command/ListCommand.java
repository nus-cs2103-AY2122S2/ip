package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;


/**
 * Represents a command that will list out all the tasks in the TaskList upon execution.
 */
public class ListCommand extends Command {


    /**
     * Executes the command listing out all the tasks currently in the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String response = "Here are your tasks:" + "\n";
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            response += i + 1 + "." + taskList.getTask(i).toString() + "\n";
        }
        return response;
    }

    /**
     * Returns True if it is an exit command and false otherwise.
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
