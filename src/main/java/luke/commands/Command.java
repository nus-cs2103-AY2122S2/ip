package luke.commands;

import luke.data.TaskList;

/**
 * Represents a command that is executable by running the execute method.
 */
public abstract class Command {

    /**
     * Return false if the command is not instanceof ExitCommand.
     * @return False if the command is not instanceof ExitCommand.
     */
    public boolean isExitCmd() {
        return false;
    }

    /**
     * Takes in a task list, execute the command and returns its command result.
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    public abstract CommandResult execute(TaskList taskList);
}
