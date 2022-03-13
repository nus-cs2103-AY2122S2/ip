package tesseract.command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.task.Task;

/**
 * Represent a command to archive a task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class ArchiveCommand extends Command {
    private static final int KEYWORD = 1;

    // The index of the task to be archived
    private int index;

    ArchiveCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    /**
     * Execute the command on the system to archive a task.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        Task task = taskList.archiveTask(this.index);
        storage.needUpdate();
        // need to write archiveTaskRes()
        return ui.archiveRes(task.toString());
    }
}
