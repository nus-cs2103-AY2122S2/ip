package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an invalid command that the program does not recognise.
 */
public class InvalidCommand extends Command {

    public InvalidCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    /**
     * Returns whether this is an exit command.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
