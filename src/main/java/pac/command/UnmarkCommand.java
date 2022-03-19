package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmark(taskIndex);
        ui.showUnmark(tasks.get(taskIndex));
        storage.writeTasks(tasks);
    }
}
