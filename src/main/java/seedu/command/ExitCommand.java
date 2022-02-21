package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;

/**
 * Displays exit message to user when run.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * Executes the exit command to return a goodbye message.
     * Checks that task list and storage passed in are still valid not null.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return Goodbye message to the user.
     */
    @Override
    public String run(TaskList taskList, Storage storage) {
        assert taskList != null : "ExitCommand->run: Task list cannot be null.";
        assert storage != null : "ExitCommand->run: Storage cannot be null.";

        return "Bye. See you later!";
    }
}
