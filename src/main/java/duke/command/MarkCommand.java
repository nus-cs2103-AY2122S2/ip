package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * MarkCommand is a Command that marks the indexed task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand takes in the index of the task to be marked as
     * done.
     * @param index the index of the task to be marked as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the indexed task as done, stores the changes in the storage list and
     * updates the user when completed.
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task markedTask = tasks.mark(index);
        storage.updateAfterMark(index);
        return Ui.markMessage(markedTask);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
