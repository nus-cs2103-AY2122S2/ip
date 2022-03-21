package pac.command;

import pac.PacException;
import pac.storage.Storage;
import pac.task.Task;
import pac.task.TaskList;
import pac.ui.Ui;

import java.io.IOException;

/**
 * Executes the reschedule command for tasks
 *  returns the ui message for Pac response
 */
public class RescheduleCommand extends Command{
    private final int taskIndex;
    private final String dateTimeStr;

    /**
     * RescheduleCommand constructor takes in a int and String
     * @param taskIndex
     * @param dateTimeStr
     */
    public RescheduleCommand(int taskIndex, String dateTimeStr) {
        this.taskIndex = taskIndex;
        this.dateTimeStr = dateTimeStr;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(taskIndex).rescheduleDate(dateTimeStr);
            storage.writeTasks(tasks);
            Task task = tasks.get(taskIndex);
            return ui.showReschedule(task);
        } catch (PacException e) {
            return ui.showPacError(e);
        } catch (IOException e) {
            return ui.showIOError(e);
        }
    }
}
