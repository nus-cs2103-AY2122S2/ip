package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that closes Duke.
 */
public class ExitCommand extends Command {

    /**
     * Instructs UI object to display the exit message before closing Duke.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object responsible for user interaction.
     * @param storage The Storage object responsible for retrieving/storing the list.
     * @return The exit message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayGoodbye();
    }

    /**
     * Returns true to indicate that this command is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
