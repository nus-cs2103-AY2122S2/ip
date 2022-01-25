package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int targetIndex;

    public DeleteCommand(int i) {
        targetIndex = i - 1;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.deleteTask(targetIndex);

        return "No problem, I've deleted that task for you:\n\n" +
                t.toString() + "\n\n" +
                "You now have " + tasks.listSize() + " task(s) remaining on your list.";
    }

    public boolean isExit() {
        return false;
    }
}
