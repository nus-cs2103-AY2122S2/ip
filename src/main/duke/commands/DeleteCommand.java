package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    protected int index;
    private static final String MESSAGE = "Got it. I've removed this task:";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.deleteTask(index);
        storage.saveTaskList(tasks);
        ui.showMessage(MESSAGE + "\n  " + t.toString() + "\n" + tasks.printTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
