package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Represents a command used to list <code>Tasks</code> within the database.
 * A <code>ListCommand</code> object corresponds to a command that returns
 * tasks registered by the user during their previous use.
 */
public class ListCommand extends Commands {

    public static final String COMMAND_WORDS = "list";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'List' Command Executed Unsuccessfully";
    private static final boolean IS_EXIT = false;

    /**
     * Returns the apt exit instruction after the command is executed.
     * If the program ends after this is executed, true is returned.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Executes the printing of all the <code>Tasks</code> registered by the users during their
     * previous use. If the command is successful, a <code>CommandResult</code> containing
     * a success message is returned, else one containing a failure message is returned.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.printFileContent() == Boolean.TRUE) {
            return new CommandResult(SUCCESS_MESSAGE);
        } else {
            System.out.println("    Failed to print database content");
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
