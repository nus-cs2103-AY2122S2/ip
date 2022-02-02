package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command and prints error message.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("This is an invalid command!");
    }

    /**
     * Returns true if command is an ExitCommand, else returns false.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

}
