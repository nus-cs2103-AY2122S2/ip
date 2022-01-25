package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
