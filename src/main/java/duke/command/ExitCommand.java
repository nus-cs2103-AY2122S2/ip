package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Objects;

/**
 * The type Exit command.
 */
public class ExitCommand extends Command {

    /**
     * Instantiates a new Exit command.
     */
    public ExitCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exited();
    }

    /**
     * The user inputs an exit command.
     */
    public boolean isExit() {
        return true;
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
