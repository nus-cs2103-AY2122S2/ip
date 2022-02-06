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
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    /**
     *
     * @param tasks An arraylist of tasks reflective of the current state in the database.
     * @param ui A class that controls the user-interface of the user.
     * @param storage A class that is in-charge of writing, appending, and reading the database.
     * @return
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.queryTasks(arguments));
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
