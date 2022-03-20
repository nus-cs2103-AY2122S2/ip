package pac.command;

import pac.PacException;
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
