package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    public InvalidCommand() {}

    /**
     * Executes the invalid command.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("This is an invalid command!");
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

}
