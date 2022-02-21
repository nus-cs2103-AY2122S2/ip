package kenobi.command;

import kenobi.storage.Storage;
import kenobi.task.TaskList;

/**
 * The Command class encapsulates the commands that can be done by Kenobi.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Executes the command.
     *
     * @return A string containing the feedback of the execution.
     */
    public abstract String execute();

    /**
     * Sets the data required for commands to execute.
     *
     * @param taskList The TaskList to which the command is to be executed.
     */
    public void setData(TaskList taskList, Storage storage) {
        tasks = taskList;
        this.storage = storage;
    }
}
