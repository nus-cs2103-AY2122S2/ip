package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command used to list Tasks within the database.
 * A ListCommand object corresponds to a command that returns
 * tasks registered by the user during their previous use.
 */
public class ListCommand extends Commands {

    public static final String COMMAND_WORDS = "list";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'List' Command Executed Unsuccessfully";
    private static final boolean IS_EXIT = false;

    /**
     * Aid in exiting the program if the command calls for it.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     * Executes the printing of all the Tasks registered by the users during their
     * previous use.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return new CommandResult(tasks.listsTask());
        } catch (Exception err) {
            return new CommandResult(FAILURE_MESSAGE);
        }
    }
}
