package athena.commands;

import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

/**
 * Represents a single command provided to Athena by the user. This is
 * an umbrella class for all types of commands.
 */
public abstract class Command {

    /**
     * Executes the command through the given ui and taskList.
     *
     * @param ui Ui instance to display outputs through.
     * @param tasklist taskList instance to run command with respect to.
     * @throws InputException If command is invalid.
     */
    public abstract void execute(Ui ui, TaskList tasklist) throws InputException;
}
