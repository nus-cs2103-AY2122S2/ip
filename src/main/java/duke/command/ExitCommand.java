package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;

/**
 * Represents a command that terminates Duke.
 *
 * @author Peter
 */
public class ExitCommand extends Command {
    /**
     * Displays an exit message.
     *
     * @param taskList List of tasks ignored.
     * @param ui       UI responsible for displaying response from Duke.
     * @param storage  Storage ignored.
     * @return String response from Duke upon successful execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) {
        return ui.showBye();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
