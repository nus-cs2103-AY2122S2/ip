package command;

import main.Storage;
import main.TaskList;
import main.TessUi;

import task.Task;

/**
 * Represent a command to delete a task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class DeleteCommand extends Command {
    protected static final int KEYWORD = 1;

    /** The index of the task to be deleted */
    protected int index;

    DeleteCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    /**
     * Execute the command on the system to delete a task.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        Task task = taskList.deleteTask(this.index);
        storage.needUpdate();
        ui.deleteTaskRes(task.toString(), taskList.size());
    };
}
