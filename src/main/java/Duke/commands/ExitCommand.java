package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;

/**
 * Deals with handling command that user exit.
 */
public class ExitCommand extends Command {

    /**
     * Saves the tasks into file and print to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return message saying goodbye.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTaskList());
        return ui.showGoodBye();
    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return true that user is exiting.
     */
    public boolean isExit() {
        return true;
    }
}
