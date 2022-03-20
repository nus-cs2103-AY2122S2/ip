package pac.command;

import pac.PacException;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            tasks.unmark(taskIndex);
            storage.writeTasks(tasks);
            return ui.showUnmark(tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBoundsError(e);
        }

    }
}
