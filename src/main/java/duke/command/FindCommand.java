package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 *
 */
public class FindCommand extends Commands {

    public static final String COMMAND_WORDS = "find";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Find' Command Executed Unsuccessfully";

    private static final boolean IS_EXIT = false;
    private final String arguments;
    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

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
     * Executes the querying of a <code>Tasks</code> object that contains a specific keyword.
     * If the creation is successful, a <code>CommandResult</code> containing a success message
     * is returned, else one containing a failrue message will be returned.
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return A command result with a string reflective of the task's success.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.queryTasks(arguments));
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
