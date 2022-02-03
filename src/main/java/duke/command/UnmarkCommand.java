package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.TaskList;
import src.main.java.duke.Ui;
import src.main.java.duke.task.Task;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.at(idx);
        tasks.at(idx).unmark();
        storage.updateAfterUnmark(idx);
        ui.unmarkMessage(temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
