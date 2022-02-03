package duke.command;

import duke.logic.Storage;
import duke.logic.TaskList;
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
     * @param ui       UI responsible for displaying exit message.
     * @param storage  Storage ignored.
     * @return <code>false</code> upon successful execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showBye();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
