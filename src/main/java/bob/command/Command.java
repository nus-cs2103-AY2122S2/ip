package bob.command;

import bob.Storage;
import bob.exception.BobException;
import bob.TaskList;
import bob.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws BobException;
}
