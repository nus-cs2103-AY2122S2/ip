package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.task.Task;
import src.main.java.duke.TaskList;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.at(idx);
        tasks.delete(idx);
        storage.updateAfterDelete(idx);
        ui.deleteMessage(temp, tasks.length());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
