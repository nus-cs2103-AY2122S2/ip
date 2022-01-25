package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the exit command to end the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    /**
     * Prints goodbye message.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printOutput("Bye! Hope to see you again soon!");
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return True.
     */
    public boolean isExit() {
        return true;
    }
}
