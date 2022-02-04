package jarvis.commands;

import jarvis.exceptions.InvalidTaskException;
import jarvis.tasks.TaskList;
import jarvis.utils.TextUI;

public abstract class Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    public void execute(TaskList dukeList, TextUI ui) throws InvalidTaskException {
        ui.printMsg(getResult(dukeList));
    }

    /**
     * Function to execute the command and get the result.
     * @param dukeList dukeList object
     * @throws InvalidTaskException invalid task
     */
    public abstract String getResult(TaskList dukeList) throws InvalidTaskException;
}
