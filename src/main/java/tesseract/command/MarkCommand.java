package tesseract.command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.task.Task;

/**
 * Represent a command to mark a task.
 *
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class MarkCommand extends Command {
    protected static final int KEYWORD = 1;
    /**
     * The index of the task to be marked
     */
    protected int index;

    MarkCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    /**
     * Execute the command on the system to mark a task as done.
     *
     * @param taskList The list of all current tasks.
     * @param ui       The user interface.
     * @param storage  The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        Task markedTask = taskList.markAsDone(this.index);
        storage.needUpdate();
        return ui.markAsDoneRes(markedTask.toString());
    }
}
