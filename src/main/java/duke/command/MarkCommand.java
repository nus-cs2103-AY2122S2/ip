package duke.command;

import duke.ui.Ui;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int i) {
        index = i;
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     * @throws DukeException if there is a problem updating the storage or user interface.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(index);
        return ui.markTask(tasks.get(index));
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
