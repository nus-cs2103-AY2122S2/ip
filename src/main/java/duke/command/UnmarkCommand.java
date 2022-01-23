package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
        storage.saveData(taskList, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
