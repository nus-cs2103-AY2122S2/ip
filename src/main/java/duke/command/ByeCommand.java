package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to exit the Duke application.
 */
public class ByeCommand extends Command {

    /**
     * Displays the goodbye message.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the text UI of Duke.
     * @param storage the storage of Duke.
     * @return a goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
