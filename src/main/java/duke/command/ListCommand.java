package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;


/**
 * Represents a command that will list out all the tasks in the TaskList upon execution.
 */
public class ListCommand extends Command {


    /**
     * Executes the command listing out all the tasks currently in the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     * @throws DukeException If there is an issue retrieving the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.print("Here are your tasks:");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            ui.print(i + 1 + "." + taskList.getTask(i).toString());
        }
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
