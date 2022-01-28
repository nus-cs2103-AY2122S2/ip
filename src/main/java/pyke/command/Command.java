package pyke.command;

import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException;
    public abstract boolean isExit();
}
