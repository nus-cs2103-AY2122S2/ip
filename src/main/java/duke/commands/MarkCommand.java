package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

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
        assert index > 0;
        this.index = index;
    }

    /**
     * Marks the indexed task as done, stores the changes in the storage list and
     * updates the user when completed.
     * @param tasks   task list local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks.getNumberOfTasks() >= this.index;
        Task markedTask = tasks.mark(index);
        storage.updateAfterMark(index);
        return Ui.showMarkedMessage(markedTask);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
