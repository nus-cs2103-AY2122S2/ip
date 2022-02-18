package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a general blueprint for a Command. A Commands is a command
 * blueprint for creation of future commands.
 */
public class Commands {

    public static final String COMMAND_WORDS = "";
    private static final boolean IS_EXIT = false;

    /**
     * Aid in exiting the program if the command calls for it.
     *
     * @return the apt exit instruction.
     */
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Represents the command provided by the user. This is an abstract class that will be used by future
     * sub-classes to dictate actions of the program.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResult("    This method is to be implemented by child classes");
    }
}
