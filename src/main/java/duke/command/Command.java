package duke.command;

import duke.util.Save;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This Command class creates a Command to be executed.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks TaskList of tasks.
     * @param ui    Ui provided.
     * @param save  Saved history.
     */
    public abstract void execute(TaskList tasks, Ui ui, Save save);
}
