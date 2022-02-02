package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException;
    public boolean isExit() {
        return false;
    }
}
