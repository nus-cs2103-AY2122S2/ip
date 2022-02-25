package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class CloneCommand extends Command {
    private int index;

    public CloneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.clone(index);
        storage.updateAfterClone(index);

        return Ui.showClonedMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
