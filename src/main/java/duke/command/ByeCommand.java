package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that prints a goodbye message to the user and causes the program to exit.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Prints goodbye message to the user.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
