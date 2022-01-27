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

    /**
     * Executes the delete command.
     *
     * @param tasks the TaskList containing the current tasks
     * @param ui the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the delete command
     * @throws DukeException if there were any errors with storage operations
     */
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
