package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Represents a command used to end the program. An <code>ExitCommand</code> object corresponds to
 * a command that exits the program.
 */
public class ExitCommand extends Commands {

    public static final String COMMAND_WORDS = "bye";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Bye' Command Executed Unsuccessfully";
    private static final boolean IS_EXIT = true;

    /**
     * Returns the apt exit instruction after the command is executed.
     * If the program ends after this is executed, true is returned.
     *
     * @return the apt exit instruction.
     */
    @Override
    public boolean isExit() {
        System.out.println("    Hope to see you again soon :(");
        System.out.println("    Let's play video game the next time!");
        return IS_EXIT;
    }

    /**
     * Executes the ending of the program. If the program termination is successful, a <code>CommandResult</code>
     * containing a success message is returned. In this case, a success_message is always returned.
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
