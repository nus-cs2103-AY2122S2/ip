package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;

/**
 * Displays welcome message to user when run.
 */
public class WelcomeCommand extends Command {

    public WelcomeCommand() {
    }

    /**
     * Executes the welcome command to return a welcome message.
     * Checks that task list and storage passed in are still valid not null.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return Welcome message to the user.
     */
    public String run(TaskList taskList, Storage storage) {
        assert taskList != null : "WelcomeCommand->run: Tasks list cannot be null.";
        assert storage != null : "WelcomeCommand->run: Storage cannot be null.";

        return "Hello I'm Calcifer.\n What can I help you with today?\n";
    }
}
