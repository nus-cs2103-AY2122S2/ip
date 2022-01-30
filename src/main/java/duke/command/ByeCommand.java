package duke.command;

import duke.util.Save;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This ByeCommand class will print the exit message when executed.
 */
public class ByeCommand extends Command {

    /**
     * Executes command by printing exit message.
     *
     * @param tasks TaskList of tasks.
     * @param ui    Ui provided.
     * @param save  Saved history.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Save save) {
        ui.showExitMessage();
    }
}
