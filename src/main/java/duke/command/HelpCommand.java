package duke.command;

import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

/**
 * Represents a command to print out a help message with a list of valid commands.
 */
public class HelpCommand extends Command {
    /**
     * Initializes the help command with user input.
     *
     * @param fullCommand The user input.
     */
    public HelpCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Prints the list of valid commands.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelp();
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
