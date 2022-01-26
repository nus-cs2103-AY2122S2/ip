package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.TaskArrayList;

public abstract class Command {
    public abstract void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException;

    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
