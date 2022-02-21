package commands;

import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to greet them.
 */
public class StartCommand extends Command {
    /** Represents the command word for the bot to greet. */
    public static final String COMMAND = "hello";

    /**
     * Returns a StartCommand object that can execute a user's instruction
     * for the bot to greet them.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showWelcome();
    }

    /**
     * The start command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
