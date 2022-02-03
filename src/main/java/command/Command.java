package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;

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
     * @throws TsundereException for any wrong input format
     */
    public abstract String execute(TaskList t, Storage s) throws TsundereException;
}
