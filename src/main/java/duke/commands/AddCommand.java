package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new add command.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command and returns the result.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of execution
     * @throws DukeException if there were any errors with storage operations
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.append(
                task.getAppendData());

        return "Task added:\n" + ui.tab(task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
