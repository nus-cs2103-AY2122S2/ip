package commands;

import storage.Storage;
import ui.DukeException;
import ui.Ui;
import tasklist.TaskList;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public abstract boolean isExit();
}
