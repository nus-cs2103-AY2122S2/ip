package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

// Enums of duke.command
// public enum Commands123 {
//     bye, list, mark, unmark, delete, todo, deadline, event;
// } 

/**
 * Represents a general blueprint for a Command. A <code>Commands</code> is a command
 * blueprint for creation of future commands.
 */
public class Commands {

    public static final String COMMAND_WORDS = "";
    private static final boolean IS_EXIT = false;

    /**
     * Returns the apt exit instruction after the command is executed.
     * If the program ends after this is executed, true is returned.
     *
     * @return the apt exit instruction.
     */
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Acts as a blueprint method meant to be overrode by future sub-classes that dictates purpose of the command.
     * If the command is successful, a <code>CommandResult</code> containing a success message is returned, else
     * one containing a failure message will be returned.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("    This method is to be implemented by child classes");
    }
}