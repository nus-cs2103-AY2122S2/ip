package duke.command;

import duke.ui.Ui;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * Represents the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     * @throws DukeException If there is a problem updating the storage or user interface.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(index);
        return ui.unmarkTask(tasks.get(index));
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
