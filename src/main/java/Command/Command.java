package Command;

import Task.TaskList;

import Main.Ui;
import Main.Storage;
import Main.TsundereException;

/**
 * abstract class Command for various different command with different execute
 */
public abstract class Command {
    /**
     * Determine if the class is ExitCommand.
     *
     * @return true if is ExitCommand, false if is not.
     */
    public abstract boolean isExit();

    /**
     * Execute various different commands
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     * @throws TsundereException
     */
    public abstract void execute(TaskList t, Ui u, Storage s) throws TsundereException;
}
