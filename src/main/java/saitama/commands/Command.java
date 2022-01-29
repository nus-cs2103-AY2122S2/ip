package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.SaitamaException;

/**
 * An abstract class of commands for Saitama.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws SaitamaException;
    public abstract boolean isExit();
}
