package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command used to end the program. An ExitCommand object corresponds to
 * a command that exits the program.
 */
public class ExitCommand extends Commands {
    public static final String COMMAND_WORDS = "bye";
    public static final String SUCCESS_MESSAGE = "    Hope to see you again soon :(\n"
            + "    Let's play video game the next time!";
    public static final String FAILURE_MESSAGE = "    'Bye' Command Executed Unsuccessfully";
    private static final boolean IS_EXIT = true;

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
     * Executes the ending of the program.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
