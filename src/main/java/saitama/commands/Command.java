package saitama.commands;

import saitama.exceptions.SaitamaException;
import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;


/**
 * An abstract class of commands for Saitama.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws SaitamaException;
    public abstract boolean isExit();
}
