package commands;

import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to terminate.
 */
public class ExitCommand extends Command {
    /** Represents the command word to terminate the bot. */
    public static final String COMMAND = "bye";

    /**
     * Returns an ExitCommand object that can execute a user's instruction
     * to terminate the bot.
     */
    public ExitCommand() {}

    /**
     * Executes the termination of the bot with a relevant interface for
     * the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showExit();
    }

    /**
     * The exit command requires that a bot terminates after its execution.
     *
     * @return Always returns true to indicate that the bot should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
