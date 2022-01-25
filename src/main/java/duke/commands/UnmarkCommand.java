package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int targetIndex;

    public UnmarkCommand(int i) {
        targetIndex = i - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.unmarkTask(targetIndex);
        storage.save(tasks);

        return "No problem, I've marked the task as uncompleted:\n" + ui.tab(t.toString());
    }

    public boolean isExit() {
        return false;
    }
}
