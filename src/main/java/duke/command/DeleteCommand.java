package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task delete = tasks.getByIndex(deleteIndex);
        tasks = tasks.remove(deleteIndex);
        storage.saveTaskList(tasks);
        ui.showMessage("Noted. I've removed this duke.task: \n        "
                + delete + "\n    Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
