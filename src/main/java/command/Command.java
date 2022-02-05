package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;

/**
 * Abstract class Command for various different command with different execute.
 */
public abstract class Command {
    /**
     * Determines if the class is ExitCommand.
     *
     * @return true if is ExitCommand, false if is not.
     */
    public abstract boolean isExit();

    /**
     * Executes various different commands.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     * @throws TsundereException for any wrong input format.
     */
    public abstract String execute(TaskList t, Storage s) throws TsundereException;
}
