package athena.commands;

import athena.exceptions.InputException;
import athena.tasks.TaskList;

/**
 * Represents a single command provided to Athena by the user. This is
 * an umbrella class for all types of commands.
 */
public abstract class Command {

    /**
     * Executes the command using the given taskList.
     *
     * @param tasklist taskList instance to run command with respect to.
     * @return Command output.
     * @throws InputException If command is invalid.
     */
    public abstract String execute(TaskList tasklist) throws InputException;
}
