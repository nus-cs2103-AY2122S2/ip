package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private final int targetIndex;

    /**
     * Creates a new unmark command.
     *
     * @param i the index of the task to be marked as undone
     */
    public UnmarkCommand(int i) {
        targetIndex = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.unmarkTask(targetIndex);
        storage.save(tasks);
        return "No problem, I've marked the task as uncompleted:\n" + ui.tab(t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
