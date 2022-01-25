package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private final int targetIndex;

    /**
     * Creates a new delete command.
     *
     * @param i the index of the task to be deleted
     */
    public DeleteCommand(int i) {
        targetIndex = i - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.deleteTask(targetIndex);
        storage.save(tasks);

        return "No problem, I've deleted that task for you:\n\n" +
                t.toString() + "\n\n" +
                "You now have " + tasks.listSize() + " task(s) remaining on your list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
