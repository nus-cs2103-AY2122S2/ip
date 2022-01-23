package helperClasses.command;

import helperClasses.Storage;
import helperClasses.TaskList;
import helperClasses.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
