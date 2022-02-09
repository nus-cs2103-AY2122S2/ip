package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (isOutOfBounds(tasks)) {
            response.append("Invalid index, please try again.");
            return;
        }

        Task task = tasks.unmark(index);
        response.append(ui.taskUnmarkedMessage(task));
        storage.save(tasks.list());
    }

    private boolean isOutOfBounds(TaskList tasks) {
        return index < 0 || index >= tasks.size();
    }
}
