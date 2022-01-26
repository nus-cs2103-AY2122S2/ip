package command;

import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index > 0 && this.index <= tasks.size()) {
            ui.showResponseMessage("delete");
            ui.showTaskMessage(tasks.get(index - 1));
            tasks.removeTask(this.index - 1);
            try {
                storage.store(tasks);
            } catch (DukeException e) {
                e.printStackTrace();
            }
            ui.printTasksCountMessage(tasks);
        } else {
            ui.showInvalidRange();
        }
    }

}
