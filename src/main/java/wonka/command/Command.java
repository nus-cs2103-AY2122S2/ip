package wonka.command;

import wonka.util.Save;
import wonka.util.TaskList;

/**
 * This Command class creates a Command to be executed.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    public abstract String execute(TaskList tasks, Save save);
}
