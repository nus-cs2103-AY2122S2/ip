package Duke.command;

import Duke.exception.DukeException;

import Duke.util.TaskList;
import Duke.util.Storage;
import Duke.util.Ui;

/**
 * This ByeCommand class will print the exit message when executed.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes command by printing exit message.
     * @param taskList List of tasks.
     * @param ui       Ui provided.
     * @param storage  Saved history.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
    }
}
