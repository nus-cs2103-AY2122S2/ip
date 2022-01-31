package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

/**
 * Deals with handling command that display list.
 */
public class ListCommand extends Command {

    /**
     * Displays the list of tasks the user have.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return string of task in the list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showList(tasks.getTaskList());
    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return false that user not exiting.
     */
    public boolean isExit() {
        return false;
    }
}
