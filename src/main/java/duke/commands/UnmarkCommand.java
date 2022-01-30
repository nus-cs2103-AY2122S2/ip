package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskException;
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
        targetIndex = i - 1;
    }

    /**
     * Executes the unmark command.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the unmark command
     * @throws DukeException if there were any errors during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (targetIndex >= tasks.listSize() || targetIndex < 0) {
            throw new InvalidTaskException();
        }

        Task t = tasks.unmarkTask(targetIndex);
        storage.save(tasks);

        return "No problem, I've marked the task as uncompleted:\n" + ui.tab(t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
