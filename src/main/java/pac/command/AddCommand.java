package pac.command;

import pac.task.Task;
import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

/**
 * Executes the add command for tasks
 * returns the ui message for Pac response
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * AddCommand constructor takes in a task
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.writeTasks(tasks);
        return ui.showAddTask(task, tasks);
    }
}
