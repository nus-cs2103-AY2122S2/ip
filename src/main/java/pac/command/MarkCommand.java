package pac.command;

import pac.PacException;
import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

/**
 * Executes the mark command for tasks
 * returns the ui message for Pac response
 */
public class MarkCommand extends Command{

    private final int taskIndex;

    /**
     * MarkCommand constructor takes in a int
     * @param taskIndex
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        try {
            tasks.mark(taskIndex);
            storage.writeTasks(tasks);
            return ui.showMark(tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            return  ui.showIndexOutOfBoundsError(e);
        }
    }
}
