package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

public class MarkCommand extends Command{

    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        tasks.mark(taskIndex);
        ui.showMark(tasks.get(taskIndex));
        storage.writeTasks(tasks);
    }
}
