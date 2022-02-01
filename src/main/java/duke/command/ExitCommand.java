package duke.command;

import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Initializes a new exit command.
     *
     * @param command The command provided by the user.
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Prints out the exit message of Duke.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printReturn("Aww. Bye! See you again soon");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
