package bobby.command;

import bobby.exception.BobbyException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

public abstract class Command {
    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;
}
