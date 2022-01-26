package command;

import command.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index > 0 && this.index <= tasks.size()) {
            ui.showResponseMessage("unmark");
            Task task = tasks.get(index - 1);
            task.unmark();
            ui.showTaskMessage(task);
        } else {
            ui.showInvalidRange();
        }
    }

}