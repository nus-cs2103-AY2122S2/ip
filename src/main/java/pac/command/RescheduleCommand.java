package pac.command;

import pac.PacException;
import pac.storage.Storage;
import pac.task.Task;
import pac.task.TaskList;
import pac.ui.Ui;

import java.io.IOException;

public class RescheduleCommand extends Command{
    private final int taskIndex;
    private final String dateTimeStr;

    public RescheduleCommand(int taskIndex, String dateTimeStr) {
        this.taskIndex = taskIndex;
        this.dateTimeStr = dateTimeStr;
    }

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
