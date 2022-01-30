package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Encapsulates a command to exit the Duke application.
 */
public class ByeCommand extends Command {

    /**
     * Displays the goodbye message.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the user interface of Duke.
     * @param storage the storage of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
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
