package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.TaskList;

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
