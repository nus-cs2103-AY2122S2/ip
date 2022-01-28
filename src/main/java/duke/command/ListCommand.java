package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     * @throws DukeException if there is a problem updating the storage or user interface.
     */
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
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
