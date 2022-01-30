package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    private final int targetIndex;

    /**
     * Creates a new mark command.
     *
     * @param i the index of the task to be marked as done
     */
    public MarkCommand(int i) {
        targetIndex = i - 1;
    }

    /**
     * Executes the mark command.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the mark command
     * @throws DukeException if there were any errors during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (targetIndex >= tasks.listSize() || targetIndex < 0) {
            throw new InvalidTaskException();
        }

        Task t = tasks.markTask(targetIndex);
        storage.save(tasks);

        return "Great job! I've marked the task as completed:\n" + ui.tab(t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
