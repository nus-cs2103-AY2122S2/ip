package command;

import main.Storage;
import main.TaskList;
import main.TessUi;
import task.Task;

/**
 * Represent a command to unmark a task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class UnmarkCommand extends Command {
    protected static final int KEYWORD = 1;
    /** The index of the task to be unmarked */
    protected int index;

    UnmarkCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    /**
     * Execute the command on the system to mark a task as undone.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        Task unmarkedTask = taskList.markAsUndone(this.index);
        return ui.markAsUndoneRes(unmarkedTask.toString());
    }
}
