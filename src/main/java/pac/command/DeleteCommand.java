package pac.command;

import pac.task.Task;
import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

/**
 * executes the delete command for tasks.
 * returns the ui message for Pac response
 */
public class DeleteCommand extends Command {

    private final int taskIndex;

    /**
     * DeleteCommand constructor takes in taskIndex
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(taskIndex);
        tasks.delete(taskIndex);
        int size = tasks.getSize();
        storage.writeTasks(tasks);
        return ui.showDelete(task, tasks, size);
    }
}
