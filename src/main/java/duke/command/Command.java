package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public abstract class Command {

    private boolean isExit = false;

    public Command() {
    }

    public void execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException {
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void startExit() {
        this.isExit = true;
    }


}
