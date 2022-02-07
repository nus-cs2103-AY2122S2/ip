package duke.command;

import java.util.Objects;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Exit command.
 */
public class ExitCommand extends Command {

    /**
     * Instantiates a new Exit command.
     */
    public ExitCommand() {}

    /**
     * Show exit message.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exited();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return obj instanceof ExitCommand;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
