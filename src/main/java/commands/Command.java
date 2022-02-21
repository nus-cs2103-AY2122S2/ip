package commands;

import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to perform.
 */
public abstract class Command {
    /**
     * Executes the user's instruction.
     *
     * @param ui the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    public abstract void execute(Ui ui, TaskList taskList);

    /**
     * Indicates whether the current command should exit after execution.
     *
     * @return Whether the current command should exit after execution.
     */
    public abstract boolean isExit();
}
