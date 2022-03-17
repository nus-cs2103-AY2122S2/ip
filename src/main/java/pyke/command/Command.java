package pyke.command;

import java.io.IOException;

import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException;
    public abstract String executeUi(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException;
    public abstract boolean isExit();
}
